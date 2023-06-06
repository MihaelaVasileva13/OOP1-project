package bg.tu_varna.sit.a1.f21621531.menu;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commands.*;

import java.io.IOException;
public class GeneralMenu implements Menu{
    private XmlFile xmlFile;
    private boolean fileOpen;
    @Override
    public void execute(String[] command) {
        try {
            if (!fileOpen&& !command[0].equals("open")) {
                throw new XMLParserException("No file is currently open!");
            }
            switch (command[0]) {
                case "open" -> open(command);
                case "save" -> save();
                case "saveas" -> saveAs(command);
                case "print" ->print();
                case "select"->select(command);
                case "set"->set(command);
                case "text"->text(command);
                case "delete"->delete(command);
                case "newchild"->newChild(command);
                case "close" -> close();
                case "help" -> help();
                default -> throw new XMLParserException("Invalid command!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            exit();
        }
    }

    private void open(String[] command) throws XMLParserException {
        if (command.length != 2 || command[1].isEmpty()) {
            throw new XMLParserException("Invalid arguments for command open!");
        }
        this.xmlFile=new XmlFile(null,null,command[1],null,null,false);
        Open open = new Open(xmlFile);
        open.execute();
        this.xmlFile = open.getXmlFile();
        Close close=new Close(xmlFile);
        close.execute();
        this.fileOpen=true;
    }
    private void save() throws XMLParserException {
        Open open=new Open(xmlFile);
        open.execute();
        Save save = new Save(xmlFile);
        save.execute();
        this.xmlFile = save.getXmlFile();
        Close close=new Close(xmlFile);
        close.execute();
    }
    private void saveAs(String[] command) throws XMLParserException, IOException {
        Open open=new Open(xmlFile);
        open.execute();
        SaveAs saveAs = new SaveAs(command, xmlFile);
        saveAs.execute();
        this.xmlFile = saveAs.getXmlFile();
        Close close=new Close(xmlFile);
        close.execute();
    }
    private void print() throws XMLParserException {
        Open open=new Open(xmlFile);
        open.execute();
        Print print=new Print(xmlFile);
        print.execute();
        Close close=new Close(xmlFile);
        close.execute();
    }
    private void select(String[] command) throws XMLParserException, IOException {
        if (command.length != 3) {
            throw new XMLParserException("Invalid arguments for command select!");
        }
        Open open=new Open(xmlFile);
        open.execute();
        Select select = new Select(xmlFile,command[1],command[2]);
        select.execute();
        Close close=new Close(xmlFile);
        close.execute();
    }
    private void set(String[] command) throws XMLParserException, IOException {
        if (command.length != 4) {
            throw new XMLParserException("Invalid arguments for command set!");
        }
        Open open=new Open(xmlFile);
        open.execute();
        Set set = new Set(xmlFile,command[1],command[2],command[3]);
        set.execute();
        Close close=new Close(xmlFile);
        close.execute();
    }
    private void text(String[] command) throws XMLParserException, IOException {
        if (command.length != 2) {
            throw new XMLParserException("Invalid arguments for command set!");
        }
        Open open=new Open(xmlFile);
        open.execute();
        Text text = new Text(xmlFile,command[1]);
        text.execute();
        Close close=new Close(xmlFile);
        close.execute();
    }
    private void delete(String[] command) throws XMLParserException, IOException {
        if (command.length != 3) {
            throw new XMLParserException("Invalid arguments for command delete!");
        }
        Open open=new Open(xmlFile);
        open.execute();
        Delete delete = new Delete(xmlFile,command[1],command[2]);
        delete.execute();
        Close close=new Close(xmlFile);
        close.execute();
    }
    private void newChild(String[] command) throws XMLParserException, IOException {
        if (command.length != 2) {
            throw new XMLParserException("Invalid arguments for command newchild!");
        }
        Open open=new Open(xmlFile);
        open.execute();
        NewChild newChild = new NewChild(xmlFile,command[1]);
        newChild.execute();
        Close close=new Close(xmlFile);
        close.execute();
    }
    private void close() throws XMLParserException {
        Close close = new Close(xmlFile);
        close.execute();
        this.xmlFile = close.getXmlFile();
        this.fileOpen=false;
    }
    private void help(){
        Help help = new Help();
        help.execute();
    }
    private void exit(){
        Exit exit = new Exit();
        exit.execute();
    }
}

