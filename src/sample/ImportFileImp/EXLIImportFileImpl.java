package sample.ImportFileImp;



import oracle.jdbc.driver.OracleConnection;


import sample.Connection.JdbcConnection;
import sample.inputFileService.IImportFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public class EXLIImportFileImpl implements IImportFile {
    private final OracleConnection connection = JdbcConnection.getInstance().getConnection();
    private String filename;

    public EXLIImportFileImpl(String filename) {
        this.filename = filename;
    }

    public EXLIImportFileImpl() {
    }


    @Override
    public List parsingFile(String tableName) {
       /* logger.info("start");
        List tmp = new ArrayList();
        Integer count = 0;
        String rowSTR = "";
        Integer cellCount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        StringJoiner joiner3 = new StringJoiner(",");
        try {
            XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(this.fileName));
            for (Iterator<XSSFSheet> it = myExcelBook.iterator(); it.hasNext(); ) {
                XSSFSheet sheet = it.next();
                Iterator<Row> rowIterator = sheet.iterator();
                Row trow = sheet.getRow(1);
                trow.forEach(cell -> joiner3.add("'" + cell.toString() + "'")
                );
                rowSTR = joiner3.toString();

                cellCount = rowSTR.split(",").length;
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    StringBuilder builder = new StringBuilder();
                    builder.append("INSERT INTO TT_");
                    builder.append(tableName);
                    builder.append(" (");

                    StringJoiner joiner = new StringJoiner(",");
                    StringJoiner joiner2 = new StringJoiner(",");

                    row.forEach(cell -> {

                        if ((cell.getColumnIndex() == 8) || (cell.getColumnIndex() == 4)) {
                            System.out.println(cell.getCellType());

                            if (cell.getCellType() == 0)
                                joiner.add("'" + dateFormat.format(cell.getDateCellValue()) + "'");
                        } else {
                            joiner.add("'" + cell.toString() + "'");
                        }
                    });
                    rowSTR = joiner.toString();
                    for (int i = 0; i < joiner.toString().split(",").length; i++) {
                        joiner2.add("field" + (i + 1));
                    }

                    builder.append(joiner2.toString());
                    builder.append(") VALUES (");
                    builder.append(joiner.toString());
                    builder.append(")");

                    tmp.add(builder.toString());
                    count++;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }

        // TODO: 14.03.2018  ошибка при повторном создании таблицы
        createTable(connection, cellCount, tableName, 500);
        logger.info("end");
        return tmp;*/
       return null;
    }

}
