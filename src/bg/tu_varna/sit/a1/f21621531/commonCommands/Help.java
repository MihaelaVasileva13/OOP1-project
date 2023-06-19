package bg.tu_varna.sit.a1.f21621531.commonCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
public class Help implements Command {
    private XmlFile xmlFile;
    @Override
    public String execute(String[] command) throws XMLParserException {
        if (command.length != 1) {
            throw new XMLParserException("Invalid arguments for command help!");
        }
        return """
            The following commands are supported:
            
            open <file>            opens <file>
            close                  closes currently opened file
            save                   saves the currently open file
            saveas <file>          saves the currently open file in <file>
            help                   prints this information
            exit                   exits the program
            print                  prints the file content formatted
            select <id> <key>      returns an attribute value by a given element id and attribute key
            set <id> <key> <value> assigning a value to an attribute
            children <id>          list of child element attributes
            child <id> <n>         access the n-th descendant of an element
            text <id>              access the text of an element
            delete <id> <key>      delete element attribute by key
            newchild <id>          adding a new element successor (the new item does not have any attributes except id)
            """;
    }
}

