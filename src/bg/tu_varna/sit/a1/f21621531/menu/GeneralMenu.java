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
    private final Map<String, Command> commandRegistry;
    public GeneralMenu() {
        this.xmlFile = new XmlFile(null, null, null, false, null, null);
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
        commandRegistry.put("exit", new Exit());
        commandRegistry.put("xpath", new XPath());
    }

    @Override
    public void execute(String[] command) throws XMLParserException, IOException {
        try {
            if (xmlFile.isFileOpen() && !command[0].equals("open")) {
                throw new XMLParserException("No file is currently open!");
            }
            if (!commandRegistry.containsKey(command[0])) {
                throw new XMLParserException("Invalid command!");
            }
            Command cmd = commandRegistry.get(command[0]);
            if (cmd instanceof XmlFileAwareCommand xmlFileAwareCmd) {
                xmlFileAwareCmd.setXmlFile(xmlFile);
                if (xmlFileAwareCmd instanceof Open) {
                    System.out.println(xmlFileAwareCmd.execute(command));
                    Close closeCommand = new Close();
                    closeCommand.setXmlFile(xmlFileAwareCmd.getXmlFile());
                    System.out.println(closeCommand.execute(new String[]{"close"}));
                } else if (xmlFileAwareCmd instanceof Save || xmlFileAwareCmd instanceof SaveAs) {
                    Open openCommand = new Open();
                    openCommand.setXmlFile(xmlFileAwareCmd.getXmlFile());
                    System.out.println(openCommand.execute(new String[]{"open", xmlFileAwareCmd.getXmlFile().getFilePath()}));
                    System.out.println(xmlFileAwareCmd.execute(command));
                    Close closeCommand = new Close();
                    closeCommand.setXmlFile(xmlFileAwareCmd.getXmlFile());
                    System.out.println(closeCommand.execute(new String[]{"close"}));
                }
                else{
                    System.out.println(xmlFileAwareCmd.execute(command));
                }
                xmlFile = xmlFileAwareCmd.getXmlFile();
            } else {
                System.out.println(cmd.execute(command));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Exit exitCommand=new Exit();
            System.out.println(exitCommand.execute(new String[]{"exit"}));
        }
    }
}





