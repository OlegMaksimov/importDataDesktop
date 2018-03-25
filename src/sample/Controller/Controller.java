package sample.Controller;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.inputFileService.IImportFile;

import java.io.File;


public class Controller {

    static String F_PATH = null;

    @FXML
    public void openImportFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Text Files", "*.txt")
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
      );
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            F_PATH = selectedFile.getAbsolutePath();
        } else {
            throw new Exception("Can't open file");
        }
    }
}
