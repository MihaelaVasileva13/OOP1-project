package bg.tu_varna.sit.a1.f21621531.commandImpl;

import bg.tu_varna.sit.a1.f21621531.commands.Save;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveImpl implements Save {
    @Override
    public void save(String filePath, String fileContent) {
        Path path= Paths.get(filePath);
        String fileName=path.getFileName().toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(fileContent);
            System.out.println("Successfully saved " + fileName);
        } catch (IOException e) {
            System.out.println("Unable to save file!");
        }
    }
}
