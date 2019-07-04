package com.spinyowl.spinygui.core.style.selector;

import com.spinyowl.spinygui.core.node.base.Node;

import java.util.List;
import java.util.Objects;

/**
 * Style selector interface.<br>
 * Style selectors are patterns used to select the elements you want to style.
 */
public interface StyleSelector {

    /**
     * Creates new selector that returns true if both selectors are applicable to node.
     *
     * @param first  first selector.
     * @param second second selector.
     * @return new selector as combination of two provided selectors.
     */
    static StyleSelector and(StyleSelector first, StyleSelector second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return t -> first.test(t) && second.test(t);
    }

    /**
     * Creates new selector that returns true if one of selectors is applicable to node.
     *
     * @param first  first selector.
     * @param second second selector.
     * @return new selector as combination of two provided selectors.
     */
    static StyleSelector or(StyleSelector first, StyleSelector second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return t -> first.test(t) || second.test(t);
    }

    /**
     * Creates new selector that returns true node could be selected with selector 'first > second'.
     * <p>
     * In general first selector should return true for node's parent, and second for node itself.
     *
     * @param first  first selector.
     * @param second second selector.
     * @return new selector as combination of two provided selectors.
     */
    static StyleSelector immediateChild(StyleSelector first, StyleSelector second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return t -> t.getParent() != null && second.test(t) && first.test(t.getParent());
    }

    /**
     * Creates new selector that returns true node could be selected with selector 'first second'.
     *
     * <p>
     * In general first selector should return true for any node's ancestor, and second for node itself.
     *
     * @param first  first selector.
     * @param second second selector.
     * @return new selector as combination of two provided selectors.
     */
    static StyleSelector child(StyleSelector first, StyleSelector second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);

        return t -> {
            boolean componentTest = second.test(t);
            if (!componentTest) return false;
            Node parent = t.getParent();
            while (parent != null) {
                if (first.test(parent)) return true;
                parent = parent.getParent();
            }
            return false;
        };
    }

    /**
     * Creates new selector that returns true node could be selected with selector 'first + second'.
     *
     * <p>
     * In general first selector should return true for node that placed immediately before tested node, and second for node itself.
     *
     * @param first  first selector.
     * @param second second selector.
     * @return new selector as combination of two provided selectors.
     */
    static StyleSelector immediateNext(StyleSelector first, StyleSelector second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return t -> {
            boolean secondTest = second.test(t);
            if (!secondTest) return false;

            Node parent = t.getParent();
            if (parent == null) return false;

            List<Node> sibilings = parent.getChildNodes();
            int indexOfComponent = sibilings.indexOf(t);
            if (indexOfComponent != 0) {
                return first.test(sibilings.get(indexOfComponent - 1));
            }
            return false;
        };
    }

    /**
     * Returns true if provided node could be selected using this selector.
     *
     * @param component node to test.
     * @return true if provided node could be selected using this selector.
     */
    boolean test(Node component);
}
