package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.IOException;
public interface Command {
    String execute(String[]command) throws IOException, XMLParserException;

    void setXmlFile(XmlFile xmlFile);

    XmlFile getXmlFile();
}
