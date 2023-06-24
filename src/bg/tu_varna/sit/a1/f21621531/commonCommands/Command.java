package bg.tu_varna.sit.a1.f21621531.commonCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;

import java.io.IOException;

public interface Command {
    String execute(String[] command) throws IOException, XMLParserException;
}
