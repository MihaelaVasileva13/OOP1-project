package bg.tu_varna.sit.a1.f21621531.xmlParserComponents;

import bg.tu_varna.sit.a1.f21621531.XmlElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdValidator {
    private final List<String> idStorage;

    public IdValidator() {
        this.idStorage = new ArrayList<>();
    }

    public XmlElement validId(XmlElement xmlElement) {
        if (xmlElement.getId() == null) {
            if (!idStorage.isEmpty()) {
                Random random = new Random();
                xmlElement.addId(String.valueOf(random.nextInt(idStorage.size())));
            } else {
                xmlElement.addId("1");
            }
        }
        String id = xmlElement.getId().replaceAll("\"", "");
        for (String s : idStorage) {
            if (xmlElement.getId().equals(s)) {
                xmlElement.addId(id + "_" + idStorage.indexOf(s));
            }
        }
        idStorage.add(xmlElement.getId());
        return xmlElement;
    }
}