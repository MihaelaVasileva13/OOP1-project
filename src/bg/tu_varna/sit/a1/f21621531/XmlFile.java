package bg.tu_varna.sit.a1.f21621531;

import java.util.ArrayList;

public class XmlFile {
    private final XmlElement rootElement;
    private final String fileName;
    private final String filePath;
    private final String fileContent;
    private final ArrayList<XmlElement> allElements;
    private final boolean fileOpened;

    public XmlFile(XmlElement rootElement, String fileName, String filePath, String fileContent, ArrayList<XmlElement> allElements, boolean fileOpened) {
        this.rootElement = rootElement;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileContent = fileContent;
        this.allElements = allElements;
        this.fileOpened = fileOpened;
    }

    public ArrayList<XmlElement> getAllElements() {
        return allElements;
    }
    public XmlElement getRootElement() {
        return rootElement;
    }
    public String getFilePath() {
        return filePath;
    }
    public String getFileContent() {
        return fileContent;
    }
    public String getFileName() {
        return fileName;
    }
}
