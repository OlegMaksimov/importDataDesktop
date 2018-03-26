package test.java.com.komtek.imoprt.ImportFileImp;



import oracle.jdbc.driver.OracleConnection;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Before;
import org.junit.Test;
import sample.Connection.JdbcConnection;
import sample.ImportFileImp.CSVIImportFileImpl;
import sample.inputFileService.IImportFile;

import java.util.List;


public class CSVIImportFileImplTest {

    private final OracleConnection connection = JdbcConnection.getInstance().getConnection();
    String path;
    private IImportFile importFile;
    String tableName;
    Logger logger = Logger.getLogger(this.getClass());
    @Before
    public void setUp() throws Exception {
        path = "c:\\Users\\LSultanova\\Desktop\\import_pac\\patient.csv";
        importFile = new CSVIImportFileImpl(path);
        tableName = "table_name2";
    }

    @Test
    public void testParsingFile() throws Exception {
        List list = importFile.parsingFile( tableName);
        System.out.println(list.get(1));
    }

    @Test
    public void testInsertToDB() throws Exception {
      /*  long start = System.currentTimeMillis();
        List list = importFile.parsingFile( tableName);
        importFile.insertToDB(connection, list, tableName);
        long finish = System.currentTimeMillis();
        logger.info("Время выполнения "+(finish-start));*/
    }


}