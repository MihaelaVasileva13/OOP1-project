package bg.tu_varna.sit.a1.f21621531.xmlParserComponents;

import bg.tu_varna.sit.a1.f21621531.XmlElement;

import java.util.ArrayList;
import java.util.Stack;
public class XmlDataExtractor {
    private XmlElement rootElement = null;
    private final IdValidator idValidator = new IdValidator();
    private final Stack<XmlElement> elementStack = new Stack<>();
    private final ArrayList<XmlElement> allElements = new ArrayList<>();
    private int currentDepth = 0;
    public void extract(String fileContent) {
        int i = 0, j = 0;
        while (i < fileContent.length()) {
            if (fileContent.charAt(i) != '<') {
                i++;
            } else {
                j++;
                if (fileContent.charAt(j) == '>') {
                    String tag = fileContent.substring(i + 1, j);
                    if (!tag.contains("/")) {
                        if (rootElement == null && !tag.contains("?")) {
                            rootElement = new XmlElement(tag);
                            elementStack.push(rootElement);
                        }
                        if (rootElement != null && !tag.contains(rootElement.getName())) {
                            String[] tagElements = tag.split("[\\s,\\\\/=]+");
                            tag = tagElements[0];
                            XmlElement currentElement;
                            if (tagElements.length > 2) {
                                currentElement = new XmlElement(tag);
                                for (int k = 1; k < tagElements.length; k++) {
                                    if (!tagElements[k].equals(currentElement.getName())) {
                                        String attributeName = tagElements[k];
                                        String attributeValue = tagElements[k + 1];
                                        if (attributeName.equals("id")) {
                                            currentElement.addId(attributeValue.replaceAll("\"", ""));
                                        } else {
                                            currentElement.addAttribute(attributeName, attributeValue.replaceAll("\"", ""));
                                        }
                                        k++;
                                    }
                                }
                            } else {
                                currentElement = new XmlElement(tag);
                            }
                            if (j < fileContent.length() - 1 && fileContent.charAt(j + 1) != '<') {
                                int endIndex = fileContent.indexOf('<', j + 1);
                                if (endIndex != -1) {
                                    String text = fileContent.substring(j + 1, endIndex).trim();
                                    if (!text.isEmpty()) {
                                        currentElement.addText(text);
                                    }
                                }
                            }
                            if (!elementStack.empty()) {
                                XmlElement parentElement = elementStack.peek();
                                currentElement = idValidator.validId(currentElement);
                                parentElement.addChildElement(currentElement);
                                allElements.add(currentElement);
                            }
                            elementStack.push(currentElement);
                            currentDepth++;
                            currentElement.setDepth(currentDepth);
                        }
                    } else {
                        elementStack.pop();
                        currentDepth--;
                    }
                    i = j;
                }
            }
        }
    }
    public XmlElement getRootElement() {
        return rootElement;
    }
    public ArrayList<XmlElement> getAllElements() {
        return allElements;
    }
    public IdValidator getIdValidator() {
        return idValidator;
    }
}
