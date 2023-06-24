package bg.tu_varna.sit.a1.f21621531.xmlParserComponents;

import bg.tu_varna.sit.a1.f21621531.xmlStructure.XmlElement;

import java.util.*;

public class IdValidator {
    private final Set<String> idStorage;

    public IdValidator() {
        this.idStorage = new HashSet<>();
    }

    public XmlElement validateId(XmlElement xmlElement) {
        if (xmlElement.getId() == null) {
            if (!idStorage.isEmpty()) {
                String randomId = generateRandomId();
                xmlElement.addId(randomId);
            } else {
                xmlElement.addId("0");
            }
        }
        String id = xmlElement.getId().replaceAll("\"", "");
        if (idStorage.contains(id)) {
            int numberInId = 1;
            String newId;
            do {
                newId = id + "_" + numberInId;
                numberInId++;
            } while (idStorage.contains(newId));
            xmlElement.addId(newId);
        }
        idStorage.add(xmlElement.getId());
        return xmlElement;
    }

    private String generateRandomId() {
        Random random = new Random();
        return String.valueOf(random.nextInt(idStorage.size()));
    }
}
