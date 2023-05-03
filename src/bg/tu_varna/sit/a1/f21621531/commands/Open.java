package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLFile;
import bg.tu_varna.sit.a1.f21621531.menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Open implements Menu {
    private final String fileName;
    private final String filePath;
    private final Path path;
    private XMLFile xmlFile;
    public Open(XMLFile xmlFile){
        this.xmlFile=xmlFile;
        this.path= Paths.get(xmlFile.getFilePath());
        this.filePath= String.valueOf(path);
        this.fileName=path.getFileName().toString();
    }
    @Override
    public void execute(String[] command) {
        try {
            if (!Files.exists(this.path)) {
                Files.createFile(this.path);
                if (Files.exists(path)) {
                    System.out.println("File created successfully");
                } else {
                    System.out.println("File creation failed");
                }
            }
            BufferedReader reader = Files.newBufferedReader(this.path);
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = reader.readLine();
            }
            this.xmlFile = new XMLFile(fileName, filePath, sb.toString(), true);
            System.out.println("Successfully opened " + fileName);
            reader.close();
        } catch (IOException e) {
            System.out.println("Unable to read file!");
        }
    }
    public XMLFile getXmlFile() {
        return xmlFile;
    }
}
