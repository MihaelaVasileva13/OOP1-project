package bg.tu_varna.sit.a1.f21621531.menu;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commonCommands.*;
import bg.tu_varna.sit.a1.f21621531.xmlParserCommands.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GeneralMenu implements Menu {
    private XmlFile xmlFile;
    private boolean fileOpen=false;
    private final Map<String, Command> commandRegistry;

    public GeneralMenu() {
        this.xmlFile = null;
        commandRegistry = new HashMap<>();
        commandRegistry.put("open", new Open());
        commandRegistry.put("save", new Save());
        commandRegistry.put("saveas", new SaveAs());
        commandRegistry.put("print", new Print());
        commandRegistry.put("select", new Select());
        commandRegistry.put("set", new Set());
        commandRegistry.put("children", new Children());
        commandRegistry.put("child", new Child());
        commandRegistry.put("text", new Text());
        commandRegistry.put("delete", new Delete());
        commandRegistry.put("newchild", new NewChild());
        commandRegistry.put("close", new Close());
        commandRegistry.put("help", new Help());
        commandRegistry.put("xpath", new XPath());
    }

    @Override
    public void execute(String[] command) throws XMLParserException, IOException {
        if (!fileOpen && !command[0].equals("open")) {
            throw new XMLParserException("No file is currently open!");
        }
        if (!commandRegistry.containsKey(command[0])) {
            throw new XMLParserException("Invalid command!");
        }
        Command cmd = commandRegistry.get(command[0]);
        if (cmd instanceof Open && fileOpen)
        {
            throw new XMLParserException("File already opened!");
        }
        if (cmd instanceof Open|| cmd instanceof Save || cmd instanceof SaveAs)
        {
            fileOpen=true;
        }
        if (cmd instanceof Close)
        {
            fileOpen=false;
        }
        if (cmd instanceof XmlFileAwareCommand xmlFileAwareCmd) {
            xmlFileAwareCmd.setXmlFile(xmlFile);
            System.out.println(xmlFileAwareCmd.execute(command));
            xmlFile = xmlFileAwareCmd.getXmlFile();
        } else {
            System.out.println(cmd.execute(command));
        }
    }
}





