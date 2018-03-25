package sample.Controller;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.ImportFileFactory.ImporFileFactory;
import sample.inputFileService.IImportFile;

import java.io.File;
import java.util.List;


public class Controller {

    static String F_PATH = null;
    static  IImportFile FILE;

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
        } else {
            throw new Exception("Can't open file");
        }

        FILE = ImporFileFactory.getImportFile(F_PATH);
    }

    @FXML
    public void onClickImportData(){

        if (!FILE.equals(null)){
            List list = FILE.parsingFile("table2");
            System.out.println(list.get(1));
        }
    }
}
