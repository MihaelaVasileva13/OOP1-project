package bg.tu_varna.sit.a1.f21621531.menu;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;

import java.io.IOException;

public interface Menu {
    void execute(String[] command) throws XMLParserException, IOException;
}
