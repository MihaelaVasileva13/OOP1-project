package bg.tu_varna.sit.a1.f21621531.xmlParserCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlStructure.XmlElement;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commonCommands.XmlFileAwareCommand;

public class Print implements XmlFileAwareCommand {
    private XmlFile xmlFile;
    private static final String INDENTATION = "\t";
    @Override
    public void setXmlFile(XmlFile xmlFile) {
        this.xmlFile = xmlFile;
    }
    @Override
    public XmlFile getXmlFile() {
        return this.xmlFile;
    }
    @Override
    public String execute(String[] command) throws XMLParserException {
        if (command.length != 1) {
            throw new XMLParserException("Invalid arguments for command print!");
        }
        return toText();
    }
    public String toText(){
        StringBuilder content = new StringBuilder();
        XmlElement element=xmlFile.getRootElement();
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
