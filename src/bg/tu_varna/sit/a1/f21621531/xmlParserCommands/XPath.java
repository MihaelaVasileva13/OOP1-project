package bg.tu_varna.sit.a1.f21621531.xmlParserCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlStructure.XmlElement;
import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;
import bg.tu_varna.sit.a1.f21621531.commonCommands.XmlFileAwareCommand;

import java.io.IOException;

public class XPath implements XmlFileAwareCommand {
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
            throw new XMLParserException("Invalid arguments for command xPath <xPath>!");
        }
        String xPath = command[1];
        StringBuilder list = new StringBuilder();
        if (xPath.contains("(") && xPath.contains(")")) {
            if (xPath.contains("=") && xPath.contains("\"") && xPath.contains("/")) {
                list = new StringBuilder(getChildElementsByText(xPath, list));
            } else if (xPath.contains("@")) {
                if (!xPath.contains("=") && !xPath.contains("\"") && !xPath.contains("/")) {
                    list = new StringBuilder(getAllElementAttribute(xPath, list));
                } else {
                    throw new XMLParserException("Invalid XPath operation!");
                }
            } else {
                throw new XMLParserException("Invalid XPath operation!");
            }
        } else if (xPath.contains("/")) {
            if (!xPath.contains("=") && !xPath.contains("\"") && !xPath.contains("@")) {
                if (xPath.contains("[") && xPath.contains("]")) {
                    list = new StringBuilder(getChildElementByIndex(xPath, list));
                } else {
                    list = new StringBuilder(getAllChildElements(xPath, list));
                }
            } else {
                throw new XMLParserException("Invalid XPath operation!");
            }
        } else {
            throw new XMLParserException("Invalid XPath operation!");
        }
        return list.toString();
    }

    private String getAllChildElements(String xPath, StringBuilder list) throws XMLParserException {
        String[] xPathParts = xPath.split("/");
        if (xPathParts.length == 2) {
            String parentName = xPathParts[0];
            String childName = xPathParts[1];
            StringBuilder elements = new StringBuilder();
            list.append("List of all ").append(childName).append("'s:\n");
            if (xmlFile.getElementByName(parentName) != null) {
                for (XmlElement element : xmlFile.getAllElements()) {
                    if (element.getName().equals(parentName)) {
                        if (!element.getChildren().isEmpty()) {
                            for (XmlElement child : element.getChildren()) {
                                if (child.getName().equals(childName)) {
                                    elements.append("- ").append(child).append("\n");
                                }
                            }
                        } else {
                            throw new XMLParserException("The parent element does not have any children!");
                        }
                    }
                }
            } else {
                throw new XMLParserException("There is not a parent element " + parentName + "!");
            }
            if (elements.isEmpty()) {
                throw new XMLParserException("There is not a child element " + childName + "!");
            } else {
                list.append(elements);
            }
        } else {
            throw new XMLParserException("Invalid XPath operation!");
        }
        return list.toString();
    }

    private String getChildElementByIndex(String xPath, StringBuilder list) throws XMLParserException {
        String[] xPathParts = xPath.split("\\W+");
        if (xPathParts.length == 3) {
            StringBuilder elements = new StringBuilder();
            String parentName = xPathParts[0];
            String childName = xPathParts[1];
            int index = Integer.parseInt(xPathParts[2]);
            int numberOfChild = 0;
            list.append("List of the ").append(index + 1).append(" ").append(childName).append(" of ").append(parentName).append(":\n");
            if (xmlFile.getElementByName(parentName) != null) {
                for (XmlElement element : xmlFile.getAllElements()) {
                    if (element.getName().equals(parentName)) {
                        if (!element.getChildren().isEmpty()) {
                            for (XmlElement child : element.getChildren()) {
                                if (child.getName().equals(childName)) {
                                    if (numberOfChild == index) {
                                        elements.append("- ").append(child).append("\n");
                                    }
                                    numberOfChild++;
                                }
                            }
                        } else {
                            throw new XMLParserException("The parent element does not have any children!");
                        }
                    }
                }
            } else {
                throw new XMLParserException("There is not a parent element " + parentName + "!");
            }
            if (elements.isEmpty()) {
                throw new XMLParserException("There is not a child element " + childName + " with index " + index + "!");
            } else {
                list.append(elements);
            }
        } else {
            throw new XMLParserException("Invalid XPath operation!");
        }
        return list.toString();
    }

    private String getAllElementAttribute(String xPath, StringBuilder list) throws XMLParserException {
        String[] xPathParts = xPath.split("\\(@|\\)");
        if (xPathParts.length == 2) {
            StringBuilder elements = new StringBuilder();
            String elementName = xPathParts[0];
            String attributeName = xPathParts[1];
            list.append("List of all ").append(attributeName).append("'s of ").append(elementName).append(":\n");
            if (xmlFile.getElementByName(elementName) != null) {
                for (XmlElement element : xmlFile.getAllElements()) {
                    if (element.getName().equals(elementName)) {
                        if (!element.getAttributes().isEmpty()) {
                            for (String key : element.getAttributes().keySet()) {
                                if (key.equals(attributeName)) {
                                    elements.append("- ").append(element.getAttributes().get(key)).append("\n");
                                }
                            }
                        }
                    }
                }
            } else {
                throw new XMLParserException("There is not element " + elementName + "!");
            }
            if (elements.isEmpty()) {
                throw new XMLParserException("There is not attribute " + attributeName + "!");
            } else {
                list.append(elements);
            }
        } else {
            throw new XMLParserException("Invalid XPath operation!");
        }
        return list.toString();
    }

    private String getChildElementsByText(String xPath, StringBuilder list) throws XMLParserException {
        String[] xPathParts = xPath.split("\\W+");
        if (xPathParts.length == 4) {
            StringBuilder elements = new StringBuilder();
            String parentName = xPathParts[0];
            String childName = xPathParts[1];
            String text = xPathParts[2];
            String childName1 = xPathParts[3];
            list.append("List of all ").append(childName1).append("'s of elements ").append(parentName).append(" with ").append(childName).append(" ").append(text).append(":\n");
            if (xmlFile.getElementByName(parentName) != null) {
                for (XmlElement element : xmlFile.getAllElements()) {
                    if (element.getName().equals(parentName)) {
                        if (!element.getChildren().isEmpty()) {
                            for (XmlElement child : element.getChildren()) {
                                if (child.toString().contains(text)) {
                                    child = xmlFile.getElementByText(child, text);
                                    if (child != null && child.getName().equals(childName)) {
                                        for (XmlElement child1 : element.getChildren()) {
                                            if (child1.getName().equals(childName1)) {
                                                elements.append("- ").append(child1).append("\n");
                                            }
                                        }
                                    } else {
                                        throw new XMLParserException("There is not such " + childName + "!");
                                    }
                                }
                            }
                        } else {
                            throw new XMLParserException("The parent element does not have any children!");
                        }
                    }
                }
            } else {
                throw new XMLParserException("There is not parent element " + parentName + "!");
            }
            if (elements.isEmpty()) {
                throw new XMLParserException("There is not a child element with this value!");
            } else {
                list.append(elements);
            }

        } else {
            throw new XMLParserException("Invalid XPath operation!");
        }
        return list.toString();
    }
}
