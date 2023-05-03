package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLFile;
import bg.tu_varna.sit.a1.f21621531.menu.InvalidCommandException;
import bg.tu_varna.sit.a1.f21621531.menu.Menu;
public class Close implements Menu {
    private XMLFile xmlFile;
    public Close(XMLFile xmlFile) throws InvalidCommandException {
        if (xmlFile.isFileOpened()) {
            throw new InvalidCommandException("No file is currently open!");
        }
        this.xmlFile=xmlFile;
    }
    @Override
    public void execute(String[] command) {
        System.out.println("Successfully closed "+xmlFile.getFileName());
        this.xmlFile=new XMLFile(null, null,null,false);
    }
    public XMLFile getXmlFile() {
        return xmlFile;
    }
}
