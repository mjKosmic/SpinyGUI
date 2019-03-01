package com.spinyowl.spinygui.core.style;

import com.spinyowl.spinygui.core.Configuration;
import com.spinyowl.spinygui.core.component.*;
import com.spinyowl.spinygui.core.component.base.Component;
import com.spinyowl.spinygui.core.component.base.Text;
import com.spinyowl.spinygui.core.converter.css3.StyleSheetException;
import com.spinyowl.spinygui.core.converter.dom.ComponentMarshaller;
import com.spinyowl.spinygui.core.style.selector.StyleSelector;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class StyleSheetFactoryTest {

    @BeforeClass
    public static void setupClass() {
        Configuration.SERVICE_PROVIDER.setValue(TestServiceProvider.class.getName());
    }

    @Test
    public void createFromCSS() throws StyleSheetException {
        String css = "panel > button .test" +
                "{" +
                "   background: #ffff80;" +
                "   color: red;" +
                "}";

        var stylesheet = StyleSheetFactory.createFromCSS(css);

        Label testLabel = new Label();
        testLabel.setClassAttribute("test");
        Component p = new Panel().add(new Button().add(testLabel));
        List<RuleSet> ruleSets = stylesheet.getRuleSets();
        RuleSet ruleSet = ruleSets.get(0);
        List<StyleSelector> selectors = ruleSet.getSelectors();

        Assert.assertFalse(selectors.get(0).test(p));
        Assert.assertTrue(selectors.get(0).test(testLabel));

        System.out.println(stylesheet);
    }

    @Test
    public void searchComponents() throws Exception {
        var css = "panel .test label { background: red; }" +
                "panel .test { background: green }" +
                "panel, .test, label { color: black; }";

        var stylesheet = StyleSheetFactory.createFromCSS(css);

        var xml = "<panel id=\"1\">\n" +
                "    <panel id=\"2\" class=\"test\">\n" +
                "        <label id=\"3\">Label 1</label>\n" +
                "    </panel>\n" +
                "    <button id=\"4\" class=\"test\"/>\n" +
                "    <panel id=\"5\" class=\"test\">\n" +
                "        <panel id=\"6\">\n" +
                "            <panel id=\"7\" class=\"test\">\n" +
                "                <label id=\"8\">Label 1</label>\n" +
                "            </panel>\n" +
                "        </panel>\n" +
                "    </panel>\n" +
                "</panel>";
        var componentTree = ComponentMarshaller.unmarshal(xml);
        System.out.println(xml);
        System.out.println(ComponentMarshaller.marshal(componentTree));

        for (RuleSet ruleSet : stylesheet.getRuleSets()) {
            System.out.println("----------------------------------");
            Set<Component> components = StyleSheet.searchComponents(ruleSet, componentTree);
            for (Component component : components) {
                System.out.println(ComponentMarshaller.marshal(component));
            }
        }


    }
}