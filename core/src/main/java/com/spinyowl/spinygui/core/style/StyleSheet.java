package com.spinyowl.spinygui.core.style;

import com.spinyowl.spinygui.core.component.base.Node;
import com.spinyowl.spinygui.core.style.selector.StyleSelector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StyleSheet {
    private List<RuleSet> ruleSets;

    public StyleSheet(List<RuleSet> ruleSets) {
        this.ruleSets = ruleSets;
    }

    public List<RuleSet> getRuleSets() {
        return ruleSets;
    }

    public static Set<Node> searchComponents(RuleSet ruleSet, Node componentTree) {
        var components = new HashSet<Node>();
        var selectors = ruleSet.getSelectors();
        for (var selector : selectors) {
            inspectComponentTree(componentTree, components, selector);
        }
        return components;
    }

    private static void inspectComponentTree(Node componentTree, HashSet<Node> components, StyleSelector selector) {
        if (selector.test(componentTree)) components.add(componentTree);
        for (Node c : componentTree.getChildNodes()) {
            inspectComponentTree(c, components, selector);
        }
    }
}
