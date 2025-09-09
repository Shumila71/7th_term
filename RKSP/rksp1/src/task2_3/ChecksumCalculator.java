package task2_3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ChecksumCalculator {

    public static int calculateChecksum(String filePath) throws IOException {
        int checksum = 0;

        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[2]; // Буфер для чтения 2 байтов (16 бит)
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                // Если прочитано 1 байт, добавляем его как 16-битное слово
                if (bytesRead == 1) {
                    checksum += ByteBuffer.wrap(new byte[]{buffer[0], 0}).getShort();
                } else {
                    // Прочитано 2 байта, добавляем их как 16-битное слово
                    checksum += ByteBuffer.wrap(buffer).getShort();
                }

                // Обработка переполнения
                checksum = (checksum & 0xFFFF) + (checksum >> 16);
            }
        }

        // Возвращаем 16-битную контрольную сумму
        return ~checksum & 0xFFFF; // Инвертируем и оставляем только 16 бит
    }

    public static void main(String[] args) {
        String filePath = "/Users/mac/IdeaProjects/rksp1/src/task2_3/test.txt"; // Укажите путь к вашему файлу

        try {
            int checksum = calculateChecksum(filePath);
            System.out.printf("16-битная контрольная сумма файла: 0x%04X%n", checksum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

