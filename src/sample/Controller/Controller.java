package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oracle.jdbc.driver.OracleConnection;
import sample.Connection.JdbcConnection;
import sample.ImportFileFactory.ImporFileFactory;
import sample.inputFileService.IImportFile;

import java.io.File;
import java.util.List;


public class Controller {

    static String F_PATH = null;
    static  IImportFile FILE;
    OracleConnection connection = JdbcConnection.getInstance().getConnection();
    @FXML
    public Label filename;
    @FXML
    public Label lblWarn;
    @FXML
    public TextField tableName;

    @FXML
    public void openImportFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Text Files", "*.txt")
                new FileChooser.ExtensionFilter("Text Files", "*.csv")
      );
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
//        System.out.println(textField.getText());
        if (selectedFile != null) {
            F_PATH = selectedFile.getAbsolutePath();
            filename.setText(selectedFile.getName());
        } else {
            throw new Exception("Can't open file");
        }

        FILE = ImporFileFactory.getImportFile(F_PATH);
    }

    @FXML
    public void onClickImportData() throws Exception {

        List list;
        if (!(FILE == null)){
            if(!(tableName.getText().isEmpty())){ // TODO: 26.03.2018 разобраться
             lblWarn.setVisible(false);
                list = FILE.parsingFile(tableName.getText());
             FILE.insertToDB(connection,list,tableName.getText());
            }
            else{
              lblWarn.setVisible(true);
            }
        }
    }
}
