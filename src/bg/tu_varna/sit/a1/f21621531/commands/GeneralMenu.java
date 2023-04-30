package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.commandImpl.*;

import java.io.IOException;

public class GeneralMenu implements Menu{
    private String filePath;
    private String fileContent;
    private boolean fileOpened=false;
    public GeneralMenu() {
        filePath = null;
    }
    @Override
    public void execute(String[] command) throws IOException {
        try {
            switch (command[0]) {
                case "open" -> open(command);
                case "save" -> save();
                case "saveas" -> saveAs(command);
                case "help" -> help();
                case "close" -> close();
                default -> throw new InvalidCommandException("Invalid option!");
            }
        }catch (InvalidCommandException e){
            System.out.println(e.getMessage());
        }
    }

    private void open(String[] command) throws InvalidCommandException {
        if (command.length != 2 || command[1].isEmpty()) {
            throw new InvalidCommandException("Invalid arguments for command open!");
        }
        this.filePath= command[1];
        Open open=new OpenImpl();
        open.open(this.filePath);
        this.fileOpened=true;
        this.fileContent= open.getFileContent();
    }
    private void save() throws InvalidCommandException {
        if (!this.fileOpened) {
            throw new InvalidCommandException("No file is currently open!");
        }
        Save save=new SaveImpl();
        save.save(this.filePath,this.fileContent);
    }
    private void saveAs(String[] command) throws InvalidCommandException, IOException {
        if (command.length != 2 || command[1].isEmpty()) {
            throw new InvalidCommandException("Invalid arguments for command saveas!");
        }
        if (!this.fileOpened) {
            throw new InvalidCommandException("No file is currently open!");
        }
        String newFilePath= command[1];
        SaveAs saveAs=new SaveAsImpl();
        saveAs.saveAs(newFilePath,this.fileContent);
    }
    private void help() {
        Help help=new HelpImpl();
        help.showOptions();
    }
    private void close() throws InvalidCommandException {
        if (!this.fileOpened) {
            throw new InvalidCommandException("No file is currently open!");
        }
        Close close=new CloseImpl();
        close.close(this.filePath);
        this.filePath=close.getFilePath();
        this.fileContent=null;
        this.fileOpened=false;
    }
}
