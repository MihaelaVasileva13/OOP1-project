package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;

import java.io.IOException;

public interface Command {
    void execute() throws IOException, XMLParserException;
}
