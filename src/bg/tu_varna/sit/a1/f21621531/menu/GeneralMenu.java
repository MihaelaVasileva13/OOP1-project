package bg.tu_varna.sit.a1.f21621531.menu;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commands.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class GeneralMenu implements Menu {
    private XmlFile xmlFile;
    private final Map<String, Command> commandRegistry;
    public GeneralMenu(){
        commandRegistry = new HashMap<>();
        this.xmlFile=new XmlFile(null,null,null,null,false,null,null);
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
        commandRegistry.put("exit", new Exit());
        commandRegistry.put("xpath", new XPath());
    }

    @Override
    public void execute(String[] command) throws XMLParserException, IOException {
        try {
            if (xmlFile.isFileOpen() && !command[0].equals("open")) {
                throw new XMLParserException("No file is currently open!");
            }
            Command cmd = commandRegistry.getOrDefault(command[0], null);
            if (cmd == null) {
                throw new XMLParserException("Invalid command!");
            }
            if (cmd instanceof Save||cmd instanceof SaveAs) {
                Open openCommand = new Open();
                openCommand.setXmlFile(xmlFile);
                System.out.println(openCommand.execute(command));
                cmd.setXmlFile(xmlFile);
                System.out.println(cmd.execute(command));
                this.xmlFile = cmd.getXmlFile();
                Close closeCommand = new Close();
                closeCommand.setXmlFile(xmlFile);
                System.out.println(closeCommand.execute(command));
            }
            else
            {
                cmd.setXmlFile(xmlFile);
                System.out.println(cmd.execute(command));
                this.xmlFile = cmd.getXmlFile();
                if (cmd instanceof Open)
                {
                    Close closeCommand = new Close();
                    closeCommand.setXmlFile(xmlFile);
                    System.out.println(closeCommand.execute(command));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}




