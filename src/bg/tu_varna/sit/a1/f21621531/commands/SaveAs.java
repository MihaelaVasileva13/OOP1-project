package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class SaveAs implements Command {
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
        if (command.length != 2 || command[1].isEmpty()) {
            throw new XMLParserException("Invalid arguments for command saveas <file>!");
        }
        String newFilePath = command[1];
        Print print = new Print();
        print.setXmlFile(xmlFile);
        try {
            File newFile = new File(newFilePath);
            String fileName = newFile.getName();
            if (!newFile.createNewFile()) {
                return ("Unable to create file!");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
                writer.write(print.execute(command));
            } catch (IOException e) {
                return ("Unable to save file!");
            }
            return ("Successfully saved " + xmlFile.getFileName()+" as "+fileName);
        } catch (IOException e) {
            return ("Unable to create file!");
        }
    }
}
