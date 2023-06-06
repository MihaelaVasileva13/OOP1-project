package bg.tu_varna.sit.a1.f21621531.xmlParserComponents;

import bg.tu_varna.sit.a1.f21621531.XmlElement;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class IdValidator {
    private final ArrayList<String>idStorage;
    public IdValidator() {
        this.idStorage = new ArrayList<>();
    }
    public XmlElement validId(XmlElement xmlElement)
    {
        int flag=0;
        if (xmlElement.getId()==null)
        {
            if (idStorage.size()!=0) {
                Random random = new Random();
                xmlElement.addId(String.valueOf(random.nextInt(idStorage.size())));
            }
            else xmlElement.addId("1");
        }
        String id=xmlElement.getId().replaceAll("\"", "");
        for (String s : idStorage) {
            if (Objects.equals(xmlElement.getId(), s)) {
                flag++;
                xmlElement.addId(id + "_" + flag);
            }
        }
        idStorage.add(xmlElement.getId());
        return xmlElement;
    }
}
