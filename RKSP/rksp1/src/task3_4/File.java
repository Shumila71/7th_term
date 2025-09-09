package task3_4;

class File {
    String type;
    int size;

    public File(String type, int size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public String toString() {
        return "File{" +
                "type='" + type + '\'' +
                ", size=" + size +
                '}';
    }
}
