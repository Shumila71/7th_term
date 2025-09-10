package task2_4;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class DirectoryWatcher {

    private static List<String> previousLines = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("D:\\code\\_7\\RKSP\\rksp1\\src\\task2_4"); // Укажите путь к вашему каталогу
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);

        System.out.println("Наблюдение за каталогом: " + path.toString());

        while (true) {
            WatchKey key;
            try {
                key = watchService.take(); // Ожидание события
            } catch (InterruptedException e) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                // Получение имени файла
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();

                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println("Создан новый файл: " + fileName);
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println("Изменен файл: " + fileName);
                    printFileChanges(path.resolve(fileName));
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println("Удален файл: " + fileName);
                    printFileSizeAndChecksum(path.resolve(fileName));
                }
            }
            key.reset();
        }
    }

    private static void printFileChanges(Path filePath) {
        try {
            List<String> currentLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

            // Сравнение строк
            List<String> addedLines = new ArrayList<>(currentLines);
            addedLines.removeAll(previousLines);

            List<String> removedLines = new ArrayList<>(previousLines);
            removedLines.removeAll(currentLines);

            if (!addedLines.isEmpty()) {
                System.out.println("Добавленные строки:");
                addedLines.forEach(System.out::println);
            }

            if (!removedLines.isEmpty()) {
                System.out.println("Удаленные строки:");
                removedLines.forEach(System.out::println);
            }

            // Обновляем предыдущие строки
            previousLines = currentLines;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFileSizeAndChecksum(Path filePath) {
        if (Files.exists(filePath)) {
            try {
                long fileSize = Files.size(filePath);
                int checksum = calculateChecksum(filePath.toString());
                System.out.println("Размер файла: " + fileSize + " байт");
                System.out.printf("Контрольная сумма: 0x%04X%n", checksum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Файл " + filePath.getFileName() + " уже удален, размер и контрольная сумма недоступны.");
        }
    }


    private static int calculateChecksum(String filePath) throws IOException {
        int checksum = 0;

        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[2]; // Буфер для чтения 2 байтов (16 бит)
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                if (bytesRead == 1) {
                    checksum += ByteBuffer.wrap(new byte[]{buffer[0], 0}).getShort();
                } else {
                    checksum += ByteBuffer.wrap(buffer).getShort();
                }
                checksum = (checksum & 0xFFFF) + (checksum >> 16);
            }
        }

        return ~checksum & 0xFFFF; // Инвертируем и оставляем
    }
}

