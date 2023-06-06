package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlElement;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.IOException;

public class Text implements Command {
    private final XmlFile xmlFile;
    private final String id;

    public Text(XmlFile xmlFile, String id) {
        this.xmlFile = xmlFile;
        this.id = id;
    }

    @Override
    public void execute() throws IOException, XMLParserException {
        boolean flag = false;
        String text =null;
        for (XmlElement element : xmlFile.getAllElements()) {
            if (element.getId().equals(id)) {
                text=element.getText();
                flag=true;
            }
        }
        if(!flag) {
            throw new XMLParserException("There is no such id!");
        }
        if (text!=null&&!text.isEmpty()) {
            System.out.println("The text of the element with id " + id + " is: " + text);
        }
        else
        {
            throw new XMLParserException("This element does not have text!");
        }
    }
}
