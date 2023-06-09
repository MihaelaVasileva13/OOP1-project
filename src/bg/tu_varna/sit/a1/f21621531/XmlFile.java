package bg.tu_varna.sit.a1.f21621531;

import bg.tu_varna.sit.a1.f21621531.xmlParserComponents.IdValidator;

import java.util.ArrayList;

public class XmlFile {
    private final XmlElement rootElement;
    private final String fileName;
    private final String filePath;
    private final String fileContent;
    private final boolean fileOpen;
    private final ArrayList<XmlElement> allElements;

    private final IdValidator idValidator;

    public XmlFile(XmlElement rootElement, String fileName, String filePath, String fileContent, boolean fileOpen, ArrayList<XmlElement> allElements, IdValidator idValidator) {
        this.rootElement = rootElement;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileContent = fileContent;
        this.fileOpen = fileOpen;
        this.allElements = allElements;
        this.idValidator = idValidator;
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
    public String getFileName() {
        return fileName;
    }
    public IdValidator getIdValidator() {
        return idValidator;
    }
    public String getFileContent() {
        return fileContent;
    }
    public boolean isFileOpen() {
        return !fileOpen;
    }
    public XmlElement getElementByName(String name) {
        for (XmlElement element : allElements) {
            if (element.getName().equals(name)) {
                return element;
            }
        }
        return null;
    }
    public XmlElement getElementById(String id) {
        for (XmlElement element : allElements) {
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }
}
