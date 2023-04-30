package bg.tu_varna.sit.a1.f21621531.commandImpl;

import bg.tu_varna.sit.a1.f21621531.commands.Open;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OpenImpl implements Open {
    private String fileContent;
    @Override
    public void open(String filePath) {
        Path path= Paths.get(filePath);
        String fileName=path.getFileName().toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = reader.readLine();
            }
            this.fileContent = sb.toString();
            System.out.println("Successfully opened " + fileName);
        } catch (IOException e) {
            System.out.println("Unable to read file!");
        }
    }
    @Override
    public String getFileContent() {
        return this.fileContent;
    }
}
