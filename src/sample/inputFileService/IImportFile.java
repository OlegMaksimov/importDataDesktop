package sample.inputFileService;



import oracle.jdbc.driver.OracleConnection;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface IImportFile<T> {
    Logger logger = Logger.getLogger("IImportFile");


    public List parsingFile(String fileName, String tableName);

    public default void createTable(OracleConnection connection, Integer colNum, String tableName, Integer lenghtField){
        logger.info("start");
        logger.info("colNum " + colNum + " tableName " + tableName + "lenghtFile " + lenghtField);
        String sql = "{ call PKG_IMPEXP.P_CREATE_TABLE(?,?,?) }";
        try {
            PreparedStatement proc = connection.prepareCall(sql);
            proc.setString(1, tableName);
            proc.setInt(2, colNum);
            proc.setInt(3, lenghtField);
            proc.execute();
        } catch (SQLException e) {
           logger.error(e.getSQLState());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("end");
    }

    ;

    public default void insertToDB(OracleConnection connection, List recordList, String tableName) throws Exception {
        logger.info("start");

        StringBuilder stringBuilder = new StringBuilder();
        Integer count = 0;
        String str = "";
        try {
            Statement statement = connection.createStatement();

            for (Object obj : recordList
                    ) {
                str = (String) obj;
                statement.executeQuery(str);
                count++;
                System.out.println(count);
            }
            statement.executeQuery("COMMIT; ");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error(str);
            throw new Exception("Ошибка при импорте");
        }

        logger.info("end");
    }

    ;
}
