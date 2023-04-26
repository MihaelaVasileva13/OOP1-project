package bg.tu_varna.sit.a1.f21621531.options;

import java.io.IOException;

public interface Menu {
    void execute(String[] options) throws InvalidOptionException, IOException;
}
