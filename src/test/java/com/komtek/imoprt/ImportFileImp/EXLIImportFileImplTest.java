package test.java.com.komtek.imoprt.ImportFileImp;


import oracle.jdbc.driver.OracleConnection;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Before;
import org.junit.Test;
import sample.Connection.JdbcConnection;
import sample.ImportFileImp.EXLIImportFileImpl;
import sample.inputFileService.IImportFile;

import java.util.List;

public class EXLIImportFileImplTest {
    private String path;
    private String tableName;
    private IImportFile importFile;
    private final OracleConnection connection = JdbcConnection.getInstance().getConnection();
    private Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setUp() throws Exception {
        path = "strah_polis_new1.xlsx";
        importFile = new EXLIImportFileImpl(path);
        tableName = "POLIC";
    }

    @Test
    public void parsingFile() {
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
        importFile.insertToDB(connection, list, tableName);
        long finish = System.currentTimeMillis();
        logger.info("Время выполнения " + (finish - start));
    }
}