package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlElement;
import bg.tu_varna.sit.a1.f21621531.XmlFile;
public class Text implements Command {
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
            throw new XMLParserException("Invalid arguments for command text <id>!");
        }
        String id=command[1];
        String text;
        XmlElement element = xmlFile.getElementById(id);
        if (element == null) {
            throw new XMLParserException("No element found with the given id!");
        }
        text = element.getText();
        if (text == null || text.isEmpty()) {
            throw new XMLParserException("This element does not have text!");
        }
        return "The text of the element with id " + id + " is: " + text;
    }
}
