package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlElement;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.IOException;

public class NewChild implements Command{
    private final XmlFile xmlFile;
    private final String id;

    public NewChild(XmlFile xmlFile, String id) {
        this.xmlFile = xmlFile;
        this.id = id;
    }
    @Override
    public void execute() throws IOException, XMLParserException {
        boolean flag = false;
        for (XmlElement element : xmlFile.getAllElements()) {
            if (element.getId().equals(id)) {
                XmlElement xmlElement=new XmlElement("newchild");
                xmlElement.addId(null);
                element.addChildElement(xmlElement);
                flag=true;
            }
        }
        if(!flag) {
            throw new XMLParserException("There is no such id!");
        }
    }
}
