import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import java.io.File;
import java.io.IOException;

public class DataLoader {
    public static DataModel loadModel(String filePath) throws IOException {
        return new FileDataModel(new File(filePath));
    }
}
