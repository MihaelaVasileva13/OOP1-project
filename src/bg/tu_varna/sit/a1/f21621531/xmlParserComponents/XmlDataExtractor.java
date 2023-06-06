package bg.tu_varna.sit.a1.f21621531.xmlParserComponents;

import bg.tu_varna.sit.a1.f21621531.XmlElement;

import java.util.ArrayList;
import java.util.Stack;
public class XmlDataExtractor {
    private XmlElement rootElement = null;
    private final IdValidator idValidator = new IdValidator();
    private final Stack<XmlElement> elementStack = new Stack<>();
    private final ArrayList<XmlElement> allElements = new ArrayList<>();

    public void extract(String fileContent) {
        int index = 0;
        int length = fileContent.length();

        while (index < length) {
            char currentChar = fileContent.charAt(index);
            if (currentChar != '<') {
                index++;
            } else {
                int closingTagIndex = fileContent.indexOf('>', index + 1);
                if (closingTagIndex != -1) {
                    String tag = fileContent.substring(index + 1, closingTagIndex);

                    if (!tag.contains("/")) {
                        if (rootElement == null && !tag.contains("?")) {
                            rootElement = new XmlElement(tag);
                            elementStack.push(rootElement);
                        } else if (rootElement != null && !tag.contains(rootElement.getName())) {
                            XmlElement currentElement = createXmlElement(tag);
                            extractTextContent(fileContent, closingTagIndex, currentElement);
                            addXmlElementToParent(currentElement);
                        }
                    } else {
                        elementStack.pop();
                    }
                    index = closingTagIndex + 1;
                }
            }
        }
    }

    private XmlElement createXmlElement(String tag) {
        String[] tagElements = tag.split("[\\s,\\\\/=]+");
        String tagName = tagElements[0];
        XmlElement currentElement = new XmlElement(tagName);

        if (tagElements.length > 2) {
            for (int k = 1; k < tagElements.length; k += 2) {
                String attributeName = tagElements[k];
                String attributeValue = tagElements[k + 1];
                if (attributeName.equals("id")) {
                    currentElement.addId(attributeValue.replaceAll("\"", ""));
                } else {
                    currentElement.addAttribute(attributeName, attributeValue.replaceAll("\"", ""));
                }
            }
        }
        return idValidator.validId(currentElement);
    }

    private void extractTextContent(String fileContent, int closingTagIndex, XmlElement currentElement) {
        int nextOpeningTagIndex = fileContent.indexOf('<', closingTagIndex + 1);
        if (nextOpeningTagIndex != -1) {
            String text = fileContent.substring(closingTagIndex + 1, nextOpeningTagIndex).trim();
            if (!text.isEmpty()) {
                currentElement.addText(text);
            }
        }
    }

    private void addXmlElementToParent(XmlElement currentElement) {
        if (!elementStack.empty()) {
            XmlElement parentElement = elementStack.peek();
            parentElement.addChildElement(currentElement);
            allElements.add(currentElement);
        }
        elementStack.push(currentElement);
    }

    public XmlElement getRootElement() {
        return rootElement;
    }

    public ArrayList<XmlElement> getAllElements() {
        return allElements;
    }
}
