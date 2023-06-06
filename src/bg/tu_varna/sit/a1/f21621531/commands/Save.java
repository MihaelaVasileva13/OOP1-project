package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save implements Command {
    private final XmlFile xmlFile;
    public Save(XmlFile xmlFile) {
        this.xmlFile = xmlFile;
    }
    @Override
    public void execute() {
        Print print=new Print(this.xmlFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.xmlFile.getFilePath()))) {
            writer.write(print.print(this.getXmlFile().getRootElement()));
            System.out.println("Successfully saved " + this.xmlFile.getFileName());
        } catch (IOException e) {
            System.out.println("Unable to save file!");
        }
    }
    public XmlFile getXmlFile() {
        return xmlFile;
    }
}
