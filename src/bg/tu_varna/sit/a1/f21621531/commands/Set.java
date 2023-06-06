package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlElement;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.IOException;
public class Set implements Command{
    private final XmlFile xmlFile;
    private final String id;
    private final String key;
    private final String value;

    public Set(XmlFile xmlFile,String id,String key,String value) {
        this.xmlFile = xmlFile;
        this.id=id;
        this.key=key;
        this.value=value;
    }
    @Override
    public void execute() throws IOException, XMLParserException {
        boolean flag=false;
        for (XmlElement element:xmlFile.getAllElements()) {
            for (String key1 : element.getAttributes().keySet()) {
                if (element.getId().contains(id)&& key1.equals(key)) {
                    element.getAttributes().replace(key,value);
                    System.out.println("The value of attribute "+key+" is set to "+value+".");
                    flag=true;
                }
            }
        }
        if(!flag) {
            throw new XMLParserException("There is no such attribute or id!");
        }
    }
}
