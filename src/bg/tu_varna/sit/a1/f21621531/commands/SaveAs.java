package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLFile;
import bg.tu_varna.sit.a1.f21621531.menu.InvalidCommandException;
import bg.tu_varna.sit.a1.f21621531.menu.Menu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveAs implements Menu {
    private final XMLFile xmlFile;
    private final String newFilePath;
    public SaveAs(String[] command, XMLFile xmlFile) throws InvalidCommandException {
        if (command.length != 2 || command[1].isEmpty()) {
            throw new InvalidCommandException("Invalid arguments for command saveas!");
        }
        if (xmlFile.isFileOpened()) {
            throw new InvalidCommandException("No file is currently open!");
        }
        this.xmlFile=xmlFile;
        this.newFilePath= command[1];
    }
    @Override
    public void execute(String[] command) throws IOException {
        File newFile = new File(this.newFilePath);
        Path path= Paths.get(this.newFilePath);
        String fileName=path.getFileName().toString();
        if(!newFile.createNewFile()){
            System.out.println("Unable to create file!");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(this.xmlFile.getFileContent());
            System.out.println("Successfully saved " + fileName);
        } catch (IOException e) {
            System.out.println("Unable to save file!");
        }
    }
    public XMLFile getXmlFile() {
        return xmlFile;
    }
}
