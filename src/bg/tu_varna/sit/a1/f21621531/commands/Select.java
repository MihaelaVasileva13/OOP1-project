package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlElement;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.IOException;
public class Select implements Command{
    private final XmlFile xmlFile;
    private final String id;
    private final String key;

    public Select(XmlFile xmlFile,String id,String key) {
        this.xmlFile = xmlFile;
        this.id=id;
        this.key=key;
    }
    @Override
    public void execute() throws IOException, XMLParserException {
        String value=null;
        for (XmlElement element:xmlFile.getAllElements()) {
            for (String key1 : element.getAttributes().keySet()) {
                if (element.getId().contains(id)&& key1.equals(key)) {
                    value = element.getAttributes().get(key).replaceAll("\"", "");
                }
            }
        }
        if (value == null || value.isEmpty()) {
            throw new XMLParserException("There is no such attribute or id!");
        }
        System.out.println("The attribute value is: "+value);
    }
}
