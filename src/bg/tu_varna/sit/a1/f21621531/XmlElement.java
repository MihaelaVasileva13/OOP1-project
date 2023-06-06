package bg.tu_varna.sit.a1.f21621531;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlElement {
    private final String name;
    private final Map<String, String> attributes;
    private final List<XmlElement> children;
    private String text;
    private String id;
    public XmlElement(String name) {
        this.name = name;
        this.attributes = new HashMap<>();
        this.children = new ArrayList<>();
        this.text = "";
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public void addId(String value) {
        id=value;
        attributes.put("id", id);
    }
    public void addChildElement(XmlElement child) {
        children.add(child);
    }
    public void addText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public List<XmlElement> getChildren() {
        return children;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("<").append(name);
            for (String key : attributes.keySet()) {
                sb.append(" ").append(key).append("=").append(attributes.get(key));
            }
            sb.append(">");
            if (!text.isEmpty()) {
                sb.append(text);
            }
            for (XmlElement child : children) {
                sb.append(child.toString());
            }
            sb.append("</").append(name).append(">");
            return sb.toString();
        }
}
