package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlElement;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.IOException;

public class Delete implements Command{
    private final XmlFile xmlFile;
    private final String id;
    private final String key;

    public Delete(XmlFile xmlFile, String id, String key) {
        this.xmlFile = xmlFile;
        this.id = id;
        this.key = key;
    }

    @Override
    public void execute() throws IOException, XMLParserException {
        boolean flag=false;
        for (XmlElement element:xmlFile.getAllElements()) {
            for (String key1 : element.getAttributes().keySet()) {
                if (element.getId().contains(id)&& key1.equals(key)) {
                    element.getAttributes().remove(key);
                    System.out.println("Attribute with id "+id+" and key "+key+" removed.");
                    flag=true;
                }
            }
        }
        if(!flag) {
            throw new XMLParserException("There is no such attribute or id!");
        }
    }
}
