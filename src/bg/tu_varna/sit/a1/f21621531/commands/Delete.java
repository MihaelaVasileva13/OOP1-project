package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlElement;
import bg.tu_varna.sit.a1.f21621531.XmlFile;
public class Delete implements Command {
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
            throw new XMLParserException("Invalid arguments for command delete!");
        }
        String id = command[1];
        String key = command[2];
        boolean flag = false;
        String result = null;
        XmlElement element = xmlFile.getElementById(id);
        if (element == null) {
            throw new XMLParserException("No element found with the given id!");
        }
        if (element.getAttributes().containsKey(key)) {
            element.getAttributes().remove(key);
            result = "The attribute " + key +" of the element with id " + id + " removed.";
            flag = true;
        }
        if (!flag) {
            throw new XMLParserException("No attribute found with the given key!");
        }
        return result;
    }
}