package sample.inputFileService;



import com.sun.javafx.tk.Toolkit;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import oracle.jdbc.driver.OracleConnection;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import sample.Controller.Controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface IImportFile<T> {
    Logger logger = Logger.getLogger("IImportFile");


    public List parsingFile( String tableName) throws Exception;

    public default void createTable(OracleConnection connection, Integer colNum, String tableName, Integer lenghtField) throws Exception {
        logger.info("start");
        logger.debug("colNum " + colNum + " tableName " + tableName + "lenghtFile " + lenghtField);
        String sql = "{ call PKG_IMPEXP.P_CREATE_TABLE(?,?,?) }";
        try {
            PreparedStatement proc = connection.prepareCall(sql);
            proc.setString(1, tableName);
            proc.setInt(2, colNum);
            proc.setInt(3, lenghtField);
            proc.execute();
        } catch (SQLException e) {
           logger.error(e.getSQLState());
           throw new SQLException(e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e);
        }
        logger.info("end");
    }

    ;

    public default void insertToDB( Statement statement, String record) throws Exception {
        logger.debug("start");
        try{
                statement.executeQuery(record);
            statement.executeQuery("COMMIT");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error(record);
            throw new Exception("Ошибка при импорте");
        }
        logger.debug("end");
    }


}
