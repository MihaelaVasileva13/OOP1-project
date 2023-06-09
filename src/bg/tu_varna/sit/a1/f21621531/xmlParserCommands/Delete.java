package bg.tu_varna.sit.a1.f21621531.xmlParserCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlStructure.XmlElement;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commonCommands.XmlFileAwareCommand;

public class Delete implements XmlFileAwareCommand {
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
            throw new XMLParserException("Invalid arguments for command delete <id> <key>!");
        }
        String id = command[1];
        String key = command[2];
        boolean attributeFound = false;
        String result = null;
        XmlElement element = xmlFile.getElementById(id);
        if (element == null) {
            throw new XMLParserException("No element found with the given id!");
        }
        if (element.getAttributes().containsKey(key)) {
            if (!key.equals("id")) {
                element.getAttributes().remove(key);
                result = "The attribute " + key + " of the element with id " + id + " removed";
                attributeFound = true;
            } else {
                throw new XMLParserException("The id can not be deleted!");
            }
        }
        if (!attributeFound) {
            throw new XMLParserException("No attribute found with the given key!");
        }
        return result;
    }
}