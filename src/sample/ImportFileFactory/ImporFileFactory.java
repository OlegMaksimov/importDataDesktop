package sample.ImportFileFactory;

import sample.ImportFileImp.CSVIImportFileImpl;
import sample.ImportFileImp.EXLIImportFileImpl;
import sample.inputFileService.IImportFile;

/**
 * Created by iMac on 25.03.2018.
 */
public class ImporFileFactory {
    public static IImportFile getImportFile(String path) {
        int index = path.indexOf('.');
        String ext = index == -1 ? null : path.substring(index);
        switch (ext) {
            case ".csv":
                return new CSVIImportFileImpl(path);
            case ".xlsx":
                return new EXLIImportFileImpl(path);
            default:
                return null;
        }
    }

}
