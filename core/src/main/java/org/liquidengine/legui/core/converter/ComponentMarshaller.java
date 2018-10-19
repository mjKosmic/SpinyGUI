package org.liquidengine.legui.core.converter;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.liquidengine.legui.core.component.base.Component;
import org.liquidengine.legui.core.component.base.ComponentMapping;
import org.liquidengine.legui.core.component.base.Text;

import java.io.StringReader;

public class ComponentMarshaller {

    public static String marshal(Component component) {
        return marshal(component, true);
    }

    public static String marshal(Component component, boolean pretty) {
        if (component == null) {
            return null;
        }

        Document document = new Document();
        Content content = createContent(component);
        document.addContent(content);

        XMLOutputter out = new XMLOutputter(new RawProcessor());
        out.setFormat(pretty ? Format.getPrettyFormat() : Format.getRawFormat());
        return out.outputString(document);
    }

    private static Content createContent(Component component) {
        if (component instanceof Text) {
            return new org.jdom2.Text(((Text) component).getText());
        } else {
            return createElement(component);
        }
    }

    private static Element createElement(Component component) {
        Element element = new Element(getTagName(component));
        for (var entry : component.getAttributes().entrySet()) {
            element.setAttribute(entry.getKey(), entry.getValue());
        }
        for (Component childComponent : component.getChildComponents()) {
            element.addContent(createContent(childComponent));
        }
        return element;
    }

    private static <T extends Component> String getTagName(T component) {
        var componentClass = component.getClass();
        if (ComponentMapping.getTagMapping().containsKey(componentClass)) {
            return ComponentMapping.getTagMapping().get(componentClass);
        } else {
            return componentClass.getCanonicalName();
        }
    }

    public static Component unmarshal(String xml) throws Exception {
        if (xml == null || xml.isEmpty())
            return null;
        return createComponentFromContent(new SAXBuilder().build(new StringReader(xml)).getRootElement());
    }

    private static Component createComponentFromContent(Content content) throws Exception {
        if (content instanceof org.jdom2.Text) {
            org.jdom2.Text text = (org.jdom2.Text) content;
            if (text.getTextTrim().isEmpty()) {
                return null;
            }
            return new Text(text.getText());
        } else if (content instanceof Element) {
            return createComponentFromElement((Element) content);
        } else return null;
    }

    private static Component createComponentFromElement(Element element) throws Exception {
        Component instance = getClassByTag(element.getName()).getDeclaredConstructor().newInstance();
        element.getAttributes().forEach(a -> instance.setAttribute(a.getName(), a.getValue()));

        for (Content c : element.getContent()) {
            try {
                Component componentFromContent = createComponentFromContent(c);
                if (componentFromContent != null) {
                    instance.addChild(componentFromContent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    private static Class<? extends Component> getClassByTag(String name) throws Exception {
        if (ComponentMapping.getTagMapping().containsValue(name)) {
            return ComponentMapping.getTagMapping().inverse().get(name);
        }
        return (Class<? extends Component>) Class.forName(name);
    }
}
