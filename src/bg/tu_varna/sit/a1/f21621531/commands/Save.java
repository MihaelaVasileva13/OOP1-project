package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save implements Command {
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
            throw new XMLParserException("Invalid arguments for command save!");
        }
        Print print = new Print();
        print.setXmlFile(xmlFile);
        String xmlContent = print.execute(command);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.xmlFile.getFilePath()))) {
            writer.write(xmlContent);
        } catch (IOException e) {
            return ("Unable to save file!");
        }
        return ("Successfully saved " + this.xmlFile.getFileName());
    }
}

