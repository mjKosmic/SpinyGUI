package com.spinyowl.spinygui.core.converter.dom;

import com.spinyowl.spinygui.core.node.base.Node;
import com.spinyowl.spinygui.core.node.base.Text;
import com.spinyowl.spinygui.core.converter.mapping.TagNameMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.StringReader;

public class NodeMarshaller {
    private static final Log LOGGER = LogFactory.getLog(NodeMarshaller.class);

    public static String marshal(Node node) {
        return marshal(node, true);
    }

    public static String marshal(Node node, boolean pretty) {
        if (node == null) {
            return null;
        }

        Document document = new Document();
        Content content = createContent(node);
        document.addContent(content);

        XMLOutputter out = new XMLOutputter(new RawProcessor());
        out.setFormat(pretty ? Format.getPrettyFormat() : Format.getRawFormat());
        return out.outputString(document);
    }

    private static Content createContent(Node node) {
        if (node instanceof Text) {
            return new org.jdom2.Text(((Text) node).getText());
        } else {
            return createElement(node);
        }
    }

    private static Element createElement(Node node) {
        Element element = new Element(getTagName(node));
        for (var entry : node.getAttributes().entrySet()) {
            element.setAttribute(entry.getKey(), entry.getValue());
        }
        for (Node childNode : node.getChildNodes()) {
            element.addContent(createContent(childNode));
        }
        return element;
    }

    private static <T extends Node> String getTagName(T component) {
        var componentClass = component.getClass();
        if (TagNameMapping.containsKey(componentClass)) {
            return TagNameMapping.get(componentClass);
        } else {
            return componentClass.getCanonicalName();
        }
    }

    // unmarshaller section

    public static Node unmarshal(String xml) throws Exception {
        if (xml == null || xml.isEmpty())
            return null;
        return createComponentFromContent(new SAXBuilder().build(new StringReader(xml)).getRootElement());
    }

    private static Node createComponentFromContent(Content content) throws Exception {
        if (content instanceof org.jdom2.Text) {
            org.jdom2.Text text = (org.jdom2.Text) content;
            if (text.getTextTrim().isEmpty()) {
                return null;
            }
            return new Text(text.getText());
        } else if (content instanceof Element) {
            return createComponentFromElement((Element) content);
        } else {
            LOGGER.warn(String.format("Can't find node mapping and class for content type '%s', content value '%s'.", content.getCType(), content.getValue()));
            return null;
        }
    }

    private static Node createComponentFromElement(Element element) throws Exception {
        Class<? extends Node> aClass = getClassByTag(element.getName());
        if (aClass == null) {
            return null;
        }
        Node instance = aClass.getDeclaredConstructor().newInstance();
        element.getAttributes().forEach(a -> instance.setAttribute(a.getName(), a.getValue()));

        for (Content c : element.getContent()) {
            try {
                Node componentFromContent = createComponentFromContent(c);
                if (componentFromContent != null) {
                    instance.addChild(componentFromContent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    private static Class<? extends Node> getClassByTag(String name) {
        if (TagNameMapping.containsTag(name)) {
            return TagNameMapping.getByTag(name);
        }
        try {
            return (Class<? extends Node>) Class.forName(name);
        } catch (ClassNotFoundException e) {
            LOGGER.warn(String.format("Can't find node mapping and class for tag '%s'.", name));
            return null;
        }
    }
}
