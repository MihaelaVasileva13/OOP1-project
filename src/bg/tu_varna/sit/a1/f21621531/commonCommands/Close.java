package bg.tu_varna.sit.a1.f21621531.commonCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
public class Close implements XmlFileAwareCommand {
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
        if (command.length != 1&& xmlFile.isFileOpen()) {
            throw new XMLParserException("Invalid arguments for command close!");
        }
        String result="Successfully closed "+xmlFile.getFileName();
        this.xmlFile=new XmlFile(null, null,null,false,null,null);
        return result;
    }
}
