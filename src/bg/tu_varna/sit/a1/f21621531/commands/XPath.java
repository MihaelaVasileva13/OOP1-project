package bg.tu_varna.sit.a1.f21621531.commands;

import bg.tu_varna.sit.a1.f21621531.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.XmlElement;
import bg.tu_varna.sit.a1.f21621531.XmlFile;

import java.io.IOException;

public class XPath implements Command {
    private XmlFile xmlFile;

    @Override
    public void setXmlFile(XmlFile xmlFile) {
        this.xmlFile = xmlFile;
    }

    @Override
    public XmlFile getXmlFile() {
        return this.xmlFile;
    }

    @Override
    public String execute(String[] command) throws IOException, XMLParserException {
        if (command.length != 2 || command[1].isEmpty()) {
            throw new XMLParserException("Invalid arguments for command xpath <XPath>!");
        }
        String xpath = command[1];
        StringBuilder list = new StringBuilder();

        list = new StringBuilder(getChildElementsByAttribute(xpath, list));

        return list.toString();
    }

    private String getAllChildElements(String xpath, StringBuilder list) {
        String[] xpathParts = xpath.split("/");
        if (xpathParts.length >= 2) {
            String parentName = xpathParts[0];
            String childName = xpathParts[1];
            list.append("List of all ").append(childName).append("'s:\n");
            if (xmlFile.getElementByName(parentName) != null) {
                for (XmlElement element : xmlFile.getAllElements()) {
                    if (element.getName().equals(parentName)) {
                        for (XmlElement child : element.getChildren()) {
                            if (child.getName().equals(childName)) {
                                list.append("- ").append(child.getText()).append("\n");
                            }
                        }
                    }
                }
            }
        }
        return list.toString();
    }

    private String getChildElementByIndex(String xpath, StringBuilder list) {
        String[] xpathParts = xpath.split("/");
        if (xpathParts.length >= 2) {
            String parentName = xpathParts[0];
            int openingBracketIndex = xpath.indexOf('[');
            int closingBracketIndex = xpath.indexOf(']');
            int index = Integer.parseInt(xpath.substring(openingBracketIndex + 1, closingBracketIndex));
            int numberOfChild = 0;
            String childName = xpath.substring(xpath.indexOf("/") + 1, openingBracketIndex);
            list.append("Index :").append(index);
            list.append("List of the ").append(index + 1).append(" ").append(childName).append(" of ").append(parentName).append(":\n");
            if (xmlFile.getElementByName(parentName) != null) {
                for (XmlElement element : xmlFile.getAllElements()) {
                    if (element.getName().equals(parentName)) {
                        for (XmlElement child : element.getChildren()) {
                            if (child.getName().equals(childName)) {
                                if (numberOfChild == index) {
                                    list.append("- ").append(child.getText()).append("\n");
                                }
                                numberOfChild++;
                            }
                        }
                    }
                }
            }
        }
        return list.toString();
    }

    private String getAllElementIds(String xpath, StringBuilder list) {
        int openingParenIndex = xpath.indexOf('(');
        int closingParenIndex = xpath.indexOf(')');
        if (openingParenIndex != -1 && closingParenIndex != -1) {
            String elementName = xpath.substring(0, openingParenIndex);
            String attributeName = xpath.substring(openingParenIndex + 1, closingParenIndex);
            list.append("List of all ").append(attributeName).append("'s:\n");
            if (xmlFile.getElementByName(elementName) != null) {
                for (XmlElement element : xmlFile.getAllElements()) {
                    if (element.getName().equals(elementName)) {
                        list.append("- ").append(element.getId()).append("\n");

                    }
                }
            }
        }
        return list.toString();
    }
    private String getChildElementsByAttribute(String xpath, StringBuilder list) {
        String[] xpathParts = xpath.split("/");
        xpath = xpath.replaceAll("\"", "");
        if (xpathParts.length >= 2) {
            String parentName = xpath.substring(0, xpath.indexOf('('));
            String childName = xpath.substring(xpath.indexOf('(') + 1, xpath.indexOf('='));
            String childName1 = xpathParts[1];
            String text = xpath.substring(xpath.indexOf('=') + 1, xpath.indexOf(')'));
            list.append("List of all ").append(childName1).append("'s of elements ").append(parentName).append(" with ").append(childName).append(" ").append(text).append(":\n");

            if (xmlFile.getElementByName(parentName) != null) {
                for (XmlElement element : xmlFile.getAllElements()) {
                    if (element.getName().equals(parentName)) {
                        if (element.getChildren().toString().contains(text))
                        {
                            for (XmlElement child : element.getChildren()) {
                                if (child.getName().equals(childName1)) {
                                    list.append("- ").append(child.getText()).append("\n");
                                }
                            }
                        }
                    }
                }
            }
        }
        return list.toString();
    }
}
