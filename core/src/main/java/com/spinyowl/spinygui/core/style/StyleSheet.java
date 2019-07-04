package com.spinyowl.spinygui.core.style;

import com.spinyowl.spinygui.core.node.base.Node;
import com.spinyowl.spinygui.core.style.selector.StyleSelector;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class StyleSheet {
    private List<RuleSet> ruleSets;

    public StyleSheet(List<RuleSet> ruleSets) {
        this.ruleSets = ruleSets;
    }

    /**
     * Used to search nodes in node tree that are correspond to specified rule set.
     *
     * @param ruleSet  rule set to search nodes.
     * @param nodeTree node tree to search nodes.
     * @return set of nodes that are correspond to specified rule set.
     */
    public static Set<Node> searchComponents(RuleSet ruleSet, Node nodeTree) {
        Objects.requireNonNull(ruleSet);
        Objects.requireNonNull(nodeTree);
        var nodes = new HashSet<Node>();
        var selectors = ruleSet.getSelectors();
        for (var selector : selectors) {
            inspectComponentTree(nodeTree, selector, nodes);
        }
        return nodes;
    }

    /**
     * Used to inspect node tree using specified selector.
     *
     * @param nodeTree node tree to search nodes.
     * @param selector selector that used to search nodes.
     * @param nodes    node set to store result.
     */
    private static void inspectComponentTree(Node nodeTree, StyleSelector selector, HashSet<Node> nodes) {
        Objects.requireNonNull(nodeTree);
        Objects.requireNonNull(selector);
        if (selector.test(nodeTree)) nodes.add(nodeTree);
        for (Node c : nodeTree.getChildNodes()) {
            inspectComponentTree(c, selector, nodes);
        }
    }

    public List<RuleSet> getRuleSets() {
        return ruleSets;
    }
}
