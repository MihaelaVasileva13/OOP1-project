package bg.tu_varna.sit.a1.f21621531.xmlParserComponents;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;
import bg.tu_varna.sit.a1.f21621531.xmlStructure.XmlElement;
import java.util.ArrayList;
import java.util.Stack;
public class XmlDataExtractor {
    private XmlElement rootElement = null;
    private final IdValidator idValidator=new IdValidator();
    private AttributeExtractor attributeExtractor = new AttributeExtractor(null,null);
    private final Stack<XmlElement> elementStack = new Stack<>();
    private final ArrayList<XmlElement> allElements = new ArrayList<>();
    private int currentDepth = 0;

    public void extract(String fileContent) throws XMLParserException {
        int i = 0, j = 0;
        Stack<String> tagStack = new Stack<>(); // Stack to track opening tags

        while (i < fileContent.length()) {
            if (fileContent.charAt(i) != '<') {
                i++;
            } else {
                j++;
                if (fileContent.charAt(j) == '>') {
                    if (j > i) {
                        String tagContent = fileContent.substring(i + 1, j);

                        // Check for invalid characters in tag content
                        if (tagContent.contains("<") || tagContent.contains(">")) {
                            throw new XMLParserException("Tag syntax error in file!");
                        }

                        // Check for consecutive angle brackets
                        if (fileContent.charAt(j + 1) == '>' || fileContent.charAt(j + 1) == '<') {
                            throw new XMLParserException("Tag syntax error in file!");
                        }

                        if (!tagContent.contains("/")) {
                            if (rootElement == null && !tagContent.contains("?")) {
                                // Create root element and push it to the element and tag stacks
                                rootElement = new XmlElement(tagContent);
                                elementStack.push(rootElement);
                                tagStack.push(tagContent);
                            } else if (rootElement != null && !tagContent.contains(rootElement.getName())) {
                                String[] tagContentElements = tagContent.split(" ");
                                tagContent = tagContentElements[0];
                                XmlElement currentElement;
                                currentElement = new XmlElement(tagContent);

                                // Process attributes of the current element
                                if (tagContentElements.length >= 2) {
                                    for (int k = 1; k < tagContentElements.length; k++) {
                                        attributeExtractor = attributeExtractor.extractAttribute(tagContentElements[k]);
                                        if (attributeExtractor.getName() != null && attributeExtractor.getValue() != null) {
                                            if (attributeExtractor.getName().equals("id")) {
                                                currentElement.addId(attributeExtractor.getValue().replaceAll("\"", ""));
                                            } else {
                                                currentElement.addAttribute(attributeExtractor.getName(), attributeExtractor.getValue());
                                            }
                                        }
                                    }
                                }

                                // Check if there is text content within the current element
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
                                    // Get the parent element and validate the current element's ID
                                    XmlElement parentElement = elementStack.peek();
                                    currentElement = idValidator.validateId(currentElement);

                                    // Add the current element to the parent element's children
                                    parentElement.addChildElement(currentElement);
                                    allElements.add(currentElement);
                                }

                                // Push the current element to the element and tag stacks
                                elementStack.push(currentElement);
                                currentDepth++;
                                currentElement.setDepth(currentDepth);
                                tagStack.push(tagContent);
                            }
                        } else {
                            if (!elementStack.isEmpty()) {
                                if (!tagStack.isEmpty()) {
                                    // Check if the closing tag matches the expected opening tag
                                    String expectedTag = tagStack.pop();
                                    if (!expectedTag.equals(tagContent.substring(1))) {
                                        throw new XMLParserException("Missing tag in file!");
                                    }
                                }
                                elementStack.pop();
                                currentDepth--;
                            } else {
                                throw new XMLParserException("Missing tag in file!");
                            }
                        }
                    } else {
                        throw new XMLParserException("Tag syntax error in file!");
                    }
                    i = j;
                }
            }
        }

        // Check if there are any unmatched opening tags
        if (!tagStack.isEmpty()) {
            throw new XMLParserException("Missing tag in file!");
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
