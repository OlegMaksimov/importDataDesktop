package test.java.com.komtek.imoprt.ImportFileImp;


import oracle.jdbc.driver.OracleConnection;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import sample.Connection.JdbcConnection;
import sample.ImportFileImp.EXLIImportFileImpl;
import sample.inputFileService.IImportFile;

import java.sql.Statement;
import java.util.List;

public class EXLIImportFileImplTest {
    private String path;
    private String tableName;
    private IImportFile importFile;
    private final OracleConnection connection = JdbcConnection.getInstance().getConnection();
    private Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setUp() throws Exception {
        path = "C:\\Users\\LSultanova\\Desktop\\прививки-сопоставление-disease_id.xlsx";
        importFile = new EXLIImportFileImpl(path);
        tableName = "TEST123";
    }

    @Test
    public void parsingFile() throws Exception {
        long start = System.currentTimeMillis();
        List list = importFile.parsingFile( tableName);
        System.out.println(list.get(1));
        long finish = System.currentTimeMillis();
        logger.info("Время выполнения " + (finish - start));
    }

    @Test
    public void testInsertToDB() throws Exception {
        long start = System.currentTimeMillis();
        List list = importFile.parsingFile( tableName);
        Statement statement =  connection.createStatement();
        for (Object record: list
             ) {
            importFile.insertToDB( statement, (String) record);
        }

        long finish = System.currentTimeMillis();
        logger.info("Время выполнения " + (finish - start));
    }
}