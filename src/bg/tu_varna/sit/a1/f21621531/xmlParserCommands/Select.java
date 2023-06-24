package bg.tu_varna.sit.a1.f21621531.xmlParserCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlStructure.XmlElement;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commonCommands.XmlFileAwareCommand;

public class Select implements XmlFileAwareCommand {
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
        if (command.length != 3 || command[1].isEmpty() || command[2].isEmpty()) {
            throw new XMLParserException("Invalid arguments for command select <id> <key>!");
        }
        String id = command[1];
        String key = command[2];
        String value = null;
        XmlElement element = xmlFile.getElementById(id);
        if (element == null) {
            throw new XMLParserException("No element found with the given id!");
        }
        for (String elementKey : element.getAttributes().keySet()) {
            if (element.getId().contains(id) && elementKey.equals(key)) {
                value = element.getAttributes().get(key).replaceAll("\"", "");
                break;
            }
        }
        if (value == null || value.isEmpty()) {
            throw new XMLParserException("The element with id " + id + " does not have the required attribute!");
        }
        return ("The attribute value of the element with id " + id + " is: " + value);
    }
}
