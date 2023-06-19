package bg.tu_varna.sit.a1.f21621531.commonCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;

public class Exit implements Command {
    @Override
    public String execute(String[] command) throws XMLParserException {
        if (command.length != 1) {
            throw new XMLParserException("Invalid arguments for command exit!");
        }
        System.out.println("Exiting the program... ");
        System.exit(0);
        return null;
    }
}
