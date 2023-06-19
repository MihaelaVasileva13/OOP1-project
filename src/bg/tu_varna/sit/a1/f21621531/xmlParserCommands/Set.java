package bg.tu_varna.sit.a1.f21621531.xmlParserCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlStructure.XmlElement;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commonCommands.XmlFileAwareCommand;

public class Set implements XmlFileAwareCommand {
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
        if (command.length != 4|| command[1].isEmpty()|| command[2].isEmpty()|| command[3].isEmpty()) {
            throw new XMLParserException("Invalid arguments for command set <id> <key> <value>!");
        }
        String id=command[1];
        String key=command[2];
        String value=command[3];
        boolean flag = false;
        String result = null;
        XmlElement element = xmlFile.getElementById(id);
        if (element == null) {
            throw new XMLParserException("No element found with the given id!");
        }
        for (String key1 : element.getAttributes().keySet()) {
            if (element.getId().contains(id) && key1.equals(key)) {
                if (!key.equals("id")) {
                    element.getAttributes().replace(key, value);
                    result = "The value of attribute " + key + " of the element with id " + id + " is set to " + value;
                    flag = true;
                    break;
                }
                else
                {
                    throw new XMLParserException("The id can not be changed!");
                }
            }
        }
        if (!flag) {
            throw new XMLParserException("The element with id " + id + " does not have the required attribute!");
        }
        return result;
    }
}

