package bg.tu_varna.sit.a1.f21621531.xmlParserComponents;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XMLParserException;

public class AttributeExtractor {
    private final String name;
    private final String value;
    public AttributeExtractor(String name, String value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }
    public AttributeExtractor extractAttribute(String attributeContent) throws XMLParserException {
        String attributeName = "";
        String attributeValue = "";
        int j,openingQuoteIndex,closingQuoteIndex;
        for (int i = 0; i < attributeContent.length(); i++) {
            if (attributeContent.charAt(i) == '=') {
                attributeName = attributeContent.substring(0, i);
                j=i+1;
                if (attributeContent.charAt(j) == '"')
                {
                    openingQuoteIndex=j;
                    j++;
                    while (j<attributeContent.length()&&attributeContent.charAt(j)!='"')
                    {
                        j++;
                    }
                    if (attributeContent.charAt(attributeContent.length()-1)!='"')
                    {
                        throw new XMLParserException("Attribute syntax error in file!");
                    }
                    closingQuoteIndex=j;
                    attributeValue=attributeContent.substring(openingQuoteIndex+1,closingQuoteIndex);
                }
                else {
                    throw  new XMLParserException("Attribute syntax error in file!");
                }
            }
        }
        if (attributeName.isEmpty()){
            throw new XMLParserException("Attribute syntax error in file!");
        }
        if (attributeValue.isEmpty()){
            throw new XMLParserException("Attribute syntax error in file!");
        }
        return new AttributeExtractor(attributeName,attributeValue);
    }
}
