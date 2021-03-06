package com.spinyowl.spinygui.core.style.css;

import com.spinyowl.spinygui.core.node.base.Element;
import com.spinyowl.spinygui.core.style.css.selector.StyleSelector;

import java.util.*;

public class StyleSheet {
    private List<RuleSet> ruleSets;

    public StyleSheet(List<RuleSet> ruleSets) {
        this.ruleSets = ruleSets;
    }

    /**
     * Used to search elements in node tree that are correspond to specified rule set.
     *
     * @param ruleSet     rule set to search elements.
     * @param elementTree element tree to search elements with rules.
     * @return set of nodes that are correspond to specified rule set.
     */
    public static Set<Element> searchElements(RuleSet ruleSet, Element elementTree) {
        Objects.requireNonNull(ruleSet);
        Objects.requireNonNull(elementTree);
        var nodes = new HashSet<Element>();
        var selectors = ruleSet.getSelectors();
        selectors.forEach(selector -> inspectElementTree(elementTree, selector, nodes));
        return nodes;
    }

    /**
     * Used to search rules in stylesheet that are correspond to specified element.
     *
     * @param styleSheet stylesheet to search for rules applicable to specified element.
     * @param element    element to search rules.
     * @return set of rules that are correspond to specified element.
     */
    public static List<RuleSet> searchRules(StyleSheet styleSheet, Element element) {
        Objects.requireNonNull(styleSheet);
        Objects.requireNonNull(element);

        ArrayList<RuleSet> result = new ArrayList<>();
        for (RuleSet ruleSet : styleSheet.ruleSets) {
            for (StyleSelector selector : ruleSet.getSelectors()) {
                if (selector.test(element)) {
                    result.add(ruleSet);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Used to inspect node tree using specified selector.
     *
     * @param elementTree element tree to search elements.
     * @param selector    selector that used to search nodes.
     * @param elements    element set to store result.
     */
    private static void inspectElementTree(Element elementTree, StyleSelector selector, HashSet<Element> elements) {
        Objects.requireNonNull(elementTree);
        Objects.requireNonNull(selector);

        if (selector.test(elementTree)) {
            elements.add(elementTree);
        }

        elementTree.getChildNodes().stream()
                .filter(n -> n instanceof Element).map(n -> (Element) n)
                .forEach(c -> inspectElementTree(c, selector, elements));
    }

    public List<RuleSet> getRuleSets() {
        return ruleSets;
    }
}
