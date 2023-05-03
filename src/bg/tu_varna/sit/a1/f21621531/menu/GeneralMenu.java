package bg.tu_varna.sit.a1.f21621531.menu;

import bg.tu_varna.sit.a1.f21621531.XMLFile;
import bg.tu_varna.sit.a1.f21621531.commands.*;

import java.io.IOException;
public class GeneralMenu implements Menu{
    private XMLFile xmlFile;
    private boolean fileClosed;
    @Override
    public void execute(String[] command) {
        try {
            if (command.length > 1 && !command[0].equals("open") && !command[0].equals("saveas")) {
                throw new InvalidCommandException("Invalid parameters for command "+command[0]+"!");
            }
            if (fileClosed && !command[0].equals("open")) {
                throw new InvalidCommandException("No file is currently open!");
            }
            switch (command[0]) {
                case "open" -> open(command);
                case "save" -> save(command);
                case "saveas" -> saveAs(command);
                case "close" -> close(command);
                case "help" -> help(command);
                default -> throw new InvalidCommandException("Invalid command!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            exit(command);
        }
    }

    private void open(String[] command) throws InvalidCommandException {
        if (command.length != 2 || command[1].isEmpty()) {
            throw new InvalidCommandException("Invalid arguments for command open!");
        }
        this.xmlFile=new XMLFile(null,command[1],null,false);
        Open open = new Open(xmlFile);
        open.execute(command);
        this.xmlFile = open.getXmlFile();
        Close close=new Close(xmlFile);
        close.execute(command);
    }
    private void save(String[] command) throws InvalidCommandException {
        Open open=new Open(xmlFile);
        open.execute(command);
        Save save = new Save(xmlFile);
        save.execute(command);
        this.xmlFile = save.getXmlFile();
        Close close=new Close(xmlFile);
        close.execute(command);
    }
    private void saveAs(String[] command) throws InvalidCommandException, IOException {
        Open open=new Open(xmlFile);
        open.execute(command);
        SaveAs saveAs = new SaveAs(command, xmlFile);
        saveAs.execute(command);
        this.xmlFile = saveAs.getXmlFile();
        Close close=new Close(xmlFile);
        close.execute(command);
    }
    private void close(String[] command) throws InvalidCommandException {
        Close close = new Close(xmlFile);
        close.execute(command);
        this.xmlFile = close.getXmlFile();
        this.fileClosed=true;
    }
    private void help(String[] command){
        Help help = new Help();
        help.execute(command);
    }
    private void exit(String[] command){
        Exit exit = new Exit();
        exit.execute(command);
    }
}

