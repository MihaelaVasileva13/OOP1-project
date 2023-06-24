package bg.tu_varna.sit.a1.f21621531.xmlParser;

import bg.tu_varna.sit.a1.f21621531.xmlParserComponents.IdValidator;
import bg.tu_varna.sit.a1.f21621531.xmlStructure.XmlElement;

import java.util.ArrayList;

public class XmlFile {
    private final XmlElement rootElement;
    private final String fileName;
    private final String filePath;
    private final ArrayList<XmlElement> allElements;
    private final IdValidator idValidator;
    private static final String INDENTATION = "\t";

    public XmlFile(XmlElement rootElement, String fileName, String filePath, ArrayList<XmlElement> allElements, IdValidator idValidator) {
        this.rootElement = rootElement;
        this.fileName = fileName;
        this.filePath = filePath;
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

    public XmlElement getElementByText(XmlElement element, String text) {
        if (!element.getText().equals(text)) {
            if (!element.getChildren().isEmpty()) {
                for (XmlElement child : element.getChildren()) {
                    if (!child.getText().equals(text)) {
                        element = (getElementByText(child, text));
                    }
                    else element=child;
                }
            }
            else {
                element = null;
            }
        }
        return element;
    }

    public String toText() {
        StringBuilder content = new StringBuilder();
        XmlElement element = getRootElement();
        content.append("<").append(element.getName());
        attributesAndTextToText(content, element);
        if (!element.getChildren().isEmpty()) {
            content.append("\n");
            for (XmlElement child : element.getChildren()) {
                content.append(childToText(child, child.getDepth()));
            }
            content.append(getIndentation(element.getDepth()));
        }
        content.append("</").append(element.getName()).append(">");
        return content.toString();
    }

    private void attributesAndTextToText(StringBuilder content, XmlElement element) {
        for (String key : element.getAttributes().keySet()) {
            content.append(" ").append(key).append("=").append("\"").append(element.getAttributes().get(key)).append("\"");
        }
        content.append(">");
        if (!element.getText().isEmpty()) {
            content.append(element.getText());
        }
    }

    private String childToText(XmlElement element, int depth) {
        StringBuilder content = new StringBuilder();
        content.append(getIndentation(depth)).append("<").append(element.getName());
        attributesAndTextToText(content, element);
        if (!element.getChildren().isEmpty()) {
            content.append("\n");
            for (XmlElement child : element.getChildren()) {
                content.append(childToText(child, depth + 1));
            }
            content.append(getIndentation(depth));
        }
        content.append("</").append(element.getName()).append(">").append("\n");
        return content.toString();
    }

    private String getIndentation(int depth) {
        return INDENTATION.repeat(Math.max(0, depth));
    }

}
