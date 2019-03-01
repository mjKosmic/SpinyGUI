package com.spinyowl.spinygui.core.style;

import com.spinyowl.spinygui.core.component.base.Component;
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

    public static Set<Component> searchComponents(RuleSet ruleSet, Component componentTree) {
        var components = new HashSet<Component>();
        var selectors = ruleSet.getSelectors();
        for (var selector : selectors) {
            inspectComponentTree(componentTree, components, selector);
        }
        return components;
    }

    private static void inspectComponentTree(Component componentTree, HashSet<Component> components, StyleSelector selector) {
        if (selector.test(componentTree)) components.add(componentTree);
        for (Component c : componentTree.getChildComponents()) {
            inspectComponentTree(c, components, selector);
        }
    }
}
