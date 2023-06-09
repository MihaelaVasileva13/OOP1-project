package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlElement;
import bg.tu_varna.sit.a1.f21621531.XmlFile;
public class Children implements Command {
    private XmlFile xmlFile;
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
        if (command.length != 2|| command[1].isEmpty()) {
            throw new XMLParserException("Invalid arguments for command children <id>!");
        }
        String id=command[1];
        XmlElement element = xmlFile.getElementById(id);
        if (element == null) {
            throw new XMLParserException("No element found with the given id!");
        }
        if (element.getChildren().isEmpty()) {
            throw new XMLParserException("The element with id "+id+" does not have any children!");
        }
        StringBuilder attributeList = new StringBuilder();
        for (XmlElement child : element.getChildren()) {
            if (!child.getAttributes().isEmpty()) {
                for (String attribute : child.getAttributes().keySet()) {
                    if (!attribute.contains("id"))
                    {
                        attributeList.append(attribute).append(" ");
                    }
                }
            }
        }
        if (attributeList.length() == 0) {
            throw new XMLParserException("The child elements of the element with id "+id+" do not have any attributes!");
        }
        return "List of attributes of the child elements of the element with id " + id + ": " + attributeList;
    }
}
