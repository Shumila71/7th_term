package task2_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class ReadFileNIO {
    public static void main(String[] args) {
        String filePath = "D:/code/_7/RKSP/rksp1/src/task2_1/file.txt";
        try {
            Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

