package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlElement;
import bg.tu_varna.sit.a1.f21621531.XmlFile;
public class Print implements Command {
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
        StringBuilder sb = new StringBuilder();
        XmlElement element=xmlFile.getRootElement();
        sb.append("<").append(element.getName());
        for (String key : element.getAttributes().keySet()) {
            sb.append(" ").append(key).append("=").append("\"").append(element.getAttributes().get(key)).append("\"");
        }
        sb.append(">");
        if (!element.getText().isEmpty()) {
            sb.append(element.getText());
        }
        if (!element.getChildren().isEmpty()) {
            sb.append("\n");
            for (XmlElement child : element.getChildren()) {
                sb.append(printChild(child, child.getDepth()));
            }
            sb.append(getIndentation(element.getDepth()));
        }
        sb.append("</").append(element.getName()).append(">");
        return sb.toString();
    }
    private String printChild(XmlElement element, int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(getIndentation(depth)).append("<").append(element.getName());
        for (String key : element.getAttributes().keySet()) {
            sb.append(" ").append(key).append("=").append("\"").append(element.getAttributes().get(key)).append("\"");
        }
        sb.append(">");
        if (!element.getText().isEmpty()) {
            sb.append(element.getText());
        }
        if (!element.getChildren().isEmpty()) {
            sb.append("\n");
            for (XmlElement child : element.getChildren()) {
                sb.append(printChild(child, depth + 1));
            }
            sb.append(getIndentation(depth));
        }
        sb.append("</").append(element.getName()).append(">").append("\n");
        return sb.toString();
    }
    private String getIndentation(int depth) {
        return INDENTATION.repeat(Math.max(0, depth));
    }
}
