package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlFile;
public class Close implements Command {
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
        this.xmlFile=new XmlFile(null, null,null,null,false,null,null);
        return result;
    }
}
