package task3_4;

class FileHandler {
    private final String fileType;

    public FileHandler(String fileType) {
        this.fileType = fileType;
    }

    public void processFile(File file) throws InterruptedException {
        if (file.type.equals(fileType)) {
            int processingTime = file.size * 7; // Время обработки
            System.out.println("Processing " + file + " for " + processingTime + " ms");
            Thread.sleep(processingTime);
            System.out.println("Processed " + file);
        } else {
            System.out.println("File type " + file.type + " not supported by this handler.");
        }
    }
}
