package sample.Task;

import javafx.concurrent.Task;
import oracle.jdbc.driver.OracleConnection;
import org.apache.log4j.Logger;
import sample.Connection.JdbcConnection;
import sample.inputFileService.IImportFile;

import java.sql.Statement;
import java.util.List;

public class ImportData extends Task<Boolean>{
    OracleConnection connection = JdbcConnection.getInstance().getConnection();
    IImportFile iImportFile;
    List recordList;

    public ImportData(IImportFile iImportFile, List recordList) {
        this.iImportFile = iImportFile;
        this.recordList = recordList;
    }

    @Override
    protected Boolean call() throws Exception {
        Logger logger = Logger.getLogger(this.getClass());
        int MAX_COUNT = recordList.size();
        StringBuilder stringBuilder = new StringBuilder();
        Statement statement = connection.createStatement();
        Integer count = 0;
        String record = "";
        for (Object obj : recordList
                ) {
            record = (String) obj;
            iImportFile.insertToDB(statement, record);
            count++;
            this.updateProgress(count, MAX_COUNT);
            System.out.println(count);
        }
        logger.debug("end");
        return true;
    }
}
