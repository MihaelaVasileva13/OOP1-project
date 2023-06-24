package bg.tu_varna.sit.a1.f21621531.xmlParserCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commonCommands.XmlFileAwareCommand;

public class Print implements XmlFileAwareCommand {
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
        if (command.length != 1) {
            throw new XMLParserException("Invalid arguments for command print!");
        }
        return xmlFile.toText();
    }
}
