package bg.tu_varna.sit.a1.f21621531.xmlParserCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlStructure.XmlElement;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commonCommands.XmlFileAwareCommand;

public class Child implements XmlFileAwareCommand {
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
            throw new XMLParserException("Invalid arguments for command child <id> <n>!");
        }
        String id = command[1];
        int n = Integer.parseInt(command[2]);
        XmlElement element = xmlFile.getElementById(id);
        if (element == null) {
            throw new XMLParserException("No element found with the given id!");
        }
        if (element.getChildren().isEmpty()) {
            throw new XMLParserException("The element with id "+ id +" does not have any children!");
        }
        if (n > element.getChildren().size()) {
            throw new XMLParserException("The element with id "+ id +" does not have " + n + " children.");
        }
        XmlElement child =  element.getChildren().get(n - 1);
        return "The child number " + n + "of the element with id "+ id +" is " + child.toString();
    }
}

