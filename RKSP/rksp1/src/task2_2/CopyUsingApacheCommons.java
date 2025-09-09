package task2_2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CopyUsingApacheCommons {
    public static void copy(String source, String destination) throws IOException {
        FileUtils.copyFile(new File(source), new File(destination));
    }
}

