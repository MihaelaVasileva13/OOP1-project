package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLFile;
import bg.tu_varna.sit.a1.f21621531.menu.InvalidCommandException;
import bg.tu_varna.sit.a1.f21621531.menu.Menu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save implements Menu {
    private final XMLFile xmlFile;
    public Save(XMLFile xmlFile) throws InvalidCommandException {
        if (xmlFile.isFileOpened()) {
            throw new InvalidCommandException("No file is currently open!");
        }
        this.xmlFile = xmlFile;
    }
    @Override
    public void execute(String[] command) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.xmlFile.getFilePath()))) {
            writer.write(this.xmlFile.getFileContent());
            System.out.println("Successfully saved " + this.xmlFile.getFileName());
        } catch (IOException e) {
            System.out.println("Unable to save file!");
        }
    }

    public XMLFile getXmlFile() {
        return xmlFile;
    }
}
