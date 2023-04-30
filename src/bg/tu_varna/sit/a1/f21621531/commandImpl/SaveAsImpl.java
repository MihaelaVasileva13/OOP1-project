package bg.tu_varna.sit.a1.f21621531.commandImpl;

import bg.tu_varna.sit.a1.f21621531.commands.SaveAs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveAsImpl implements SaveAs {
    @Override
    public void saveAs(String filePath, String fileContent) throws IOException {
        File newFile = new File(filePath);
        Path path= Paths.get(filePath);
        String fileName=path.getFileName().toString();
        if(!newFile.createNewFile()){
            System.out.println("Unable to create file!");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(fileContent);
            System.out.println("Successfully saved " + fileName);
        } catch (IOException e) {
            System.out.println("Unable to save file!");
        }
    }
}
