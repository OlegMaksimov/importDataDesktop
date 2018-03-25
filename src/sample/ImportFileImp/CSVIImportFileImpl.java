package sample.ImportFileImp;



import oracle.jdbc.driver.OracleConnection;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import sample.Connection.JdbcConnection;
import sample.inputFileService.IImportFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class CSVIImportFileImpl implements IImportFile {

    private final OracleConnection connection = JdbcConnection.getInstance().getConnection();
    Logger logger = Logger.getLogger(this.getClass());
//    java.util.logging.Logger logger = java.util.logging.Logger.getLogger("CSVIImportFileImpl");
    @Override
    public List parsingFile(String fileName, String tableName) {
        Path path = Paths.get(fileName);
        logger.info("start");
        try {

            List temp = Files.readAllLines(path, Charset.forName("windows-1251"));

            List result = (List) temp.stream().map(o -> {
                        String str = (String) o;
                        String[] strarr = str.split(";");
                        str = "INSERT INTO TT_" + tableName + " (";
                        StringJoiner stringJoiner = new StringJoiner(",");
                        for (int i = 0; i < strarr.length; i++) {
                            stringJoiner.add("field" + (i + 1));
                        }
                        str += stringJoiner.toString() + ") VALUES (";
                        stringJoiner = new StringJoiner(",");
                        for (int i = 0; i < strarr.length; i++) {

                            stringJoiner.add(strarr[i].replaceAll("\"", "'"));
                        }
                        str += stringJoiner.toString();
//                str = str.substring(0,str.length()-2);
                        str += ")";
                        return str;
                    }
            ).collect(Collectors.toList());

            String row = (String) temp.get(0);
            // TODO: 14.03.2018  ошибка при повторном создании таблицы 
//            createTable(connection, row.split(";").length, tableName, 500);
            logger.info("end");
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
