package bg.tu_varna.sit.a1.f21621531.commonCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
import bg.tu_varna.sit.a1.f21621531.xmlParserComponents.XmlDataExtractor;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Open implements XmlFileAwareCommand {
    private XmlFile xmlFile;
    private final XmlDataExtractor xmlDataExtractor = new XmlDataExtractor();
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
            throw new XMLParserException("Invalid arguments for command open <file>!");
        }
        Path path = Path.of(command[1]);
        String filePath = path.toString();
        String fileName = path.getFileName().toString();
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                if (!Files.exists(path)) {
                    throw new IOException("File creation failed!");
                }
            }
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                StringBuilder fileContent = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    fileContent.append(line);
                    fileContent.append(System.lineSeparator());
                    line = reader.readLine();
                }
                xmlDataExtractor.extract(fileContent.toString());
                xmlFile = new XmlFile(xmlDataExtractor.getRootElement(), fileName, filePath, xmlDataExtractor.getAllElements(), xmlDataExtractor.getIdValidator());
            }
        } catch (IOException e) {
            return "Unable to read the file: " + e.getMessage();
        }

        return "Successfully opened " + xmlFile.getFileName();
    }
}

