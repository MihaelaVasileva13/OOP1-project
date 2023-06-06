package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

public class Close implements Command {
    private XmlFile xmlFile;
    public Close(XmlFile xmlFile) throws XMLParserException {
        this.xmlFile=xmlFile;
    }
    @Override
    public void execute() {
        System.out.println("Successfully closed "+xmlFile.getFileName());
        this.xmlFile=new XmlFile(null, null,null,null,null,false);
    }
    public XmlFile getXmlFile() {
        return xmlFile;
    }
}
