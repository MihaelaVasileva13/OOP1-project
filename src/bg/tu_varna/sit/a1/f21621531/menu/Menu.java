package bg.tu_varna.sit.a1.f21621531.menu;

import java.io.IOException;
public interface Menu {
     void execute(String[] command) throws InvalidCommandException, IOException;
}