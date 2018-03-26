package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oracle.jdbc.driver.OracleConnection;
import org.apache.log4j.Logger;
import sample.Connection.JdbcConnection;
import sample.ImportFileFactory.ImporFileFactory;
import sample.Task.ImportData;
import sample.inputFileService.IImportFile;

import java.io.File;
import java.util.List;


public class Controller {

    Logger logger = Logger.getLogger(this.getClass());
    static String F_PATH = null;
    static  IImportFile FILE;
    OracleConnection connection = JdbcConnection.getInstance().getConnection();
    private ImportData importDataTask;
    @FXML
    public Label filename;
    @FXML
    public Label lblWarn;
    @FXML
    public TextField tableName;
    @FXML
    public ProgressBar pgrBar;
    @FXML
    public ProgressIndicator pgrInd;

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
            if(!(tableName.getText().isEmpty())){
             lblWarn.setVisible(false);
                list = FILE.parsingFile(tableName.getText());
                importDataTask = new ImportData(FILE,list);
                pgrBar.progressProperty().unbind();
                pgrBar.progressProperty().bind(importDataTask.progressProperty());
                pgrInd.progressProperty().unbind();
                pgrInd.progressProperty().bind(importDataTask.progressProperty());
                new Thread(importDataTask).start();
//             FILE.insertToDB(connection,list,tableName.getText());
            }
            else{
              lblWarn.setVisible(true);
            }
        }
    }




    public  void setProgres(Double progres) throws Exception {
        try {
            if (pgrBar ==null || pgrInd ==null) {
                pgrBar = new ProgressBar();
                pgrInd = new ProgressIndicator();
            }
            pgrBar.setProgress(0.0+progres);
            pgrInd.setProgress(0.0+progres);

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e);
        }
    }
}
