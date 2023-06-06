package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
public class SaveAs implements Command {
    private final XmlFile xmlFile;
    private final String newFilePath;
    public SaveAs(String[] command, XmlFile xmlFile) throws XMLParserException {
        if (command.length != 2 || command[1].isEmpty()) {
            throw new XMLParserException("Invalid arguments for command saveas!");
        }
        this.xmlFile=xmlFile;
        this.newFilePath= command[1];
    }
    @Override
    public void execute() throws IOException {
        Print print=new Print(this.xmlFile);
        File newFile = new File(this.newFilePath);
        Path path= Paths.get(this.newFilePath);
        String fileName=path.getFileName().toString();
        if(!newFile.createNewFile()){
            System.out.println("Unable to create file!");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(print.print(this.xmlFile.getRootElement()));
            System.out.println("Successfully saved " + fileName);
        } catch (IOException e) {
            System.out.println("Unable to save file!");
        }
    }
    public XmlFile getXmlFile() {
        return xmlFile;
    }
}
