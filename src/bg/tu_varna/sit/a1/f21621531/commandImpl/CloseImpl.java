package bg.tu_varna.sit.a1.f21621531.commandImpl;

import bg.tu_varna.sit.a1.f21621531.commands.Close;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CloseImpl implements Close {
    private String filePath;
    @Override
    public void close(String filePath) {
        Path path= Paths.get(filePath);
        String fileName=path.getFileName().toString();
        System.out.println("Successfully closed "+fileName);
        this.filePath=null;
    }
    @Override
    public String getFilePath() {
        return this.filePath;
    }
}
