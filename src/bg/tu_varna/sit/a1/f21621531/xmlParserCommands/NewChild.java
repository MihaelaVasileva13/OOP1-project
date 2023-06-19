package bg.tu_varna.sit.a1.f21621531.xmlParserCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlStructure.XmlElement;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commonCommands.XmlFileAwareCommand;

public class NewChild implements XmlFileAwareCommand {
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
        if (command.length != 3|| command[1].isEmpty()|| command[2].isEmpty()) {
            throw new XMLParserException("Invalid arguments for command newchild <id>!");
        }
        String id=command[1];
        String name=command[2];
        boolean flag = false;
        XmlElement parentElement = null;
        for (XmlElement element : xmlFile.getAllElements()) {
            if (element.getId().equals(id)) {
                parentElement = element;
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new XMLParserException("No element found with the given id!");
        }
        XmlElement newElement = new XmlElement(name);
        xmlFile.getIdValidator().validateId(newElement);
        parentElement.addChildElement(newElement);
        return "New child with name " + name + " added successfully to element with id " + id;
    }
}
