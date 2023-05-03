package bg.tu_varna.sit.a1.f21621531;

public class XMLFile {
    private final String fileName;
    private final String filePath;
    private final String fileContent;
    private final boolean fileOpened;
    public XMLFile(String fileName, String filePath, String fileContent, boolean fileOpened) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileContent = fileContent;
        this.fileOpened = fileOpened;
    }
    public String getFilePath() {
        return filePath;
    }
    public String getFileContent() {
        return fileContent;
    }
    public boolean isFileOpened() {
        return !fileOpened;
    }
    public String getFileName() {
        return fileName;
    }
}
