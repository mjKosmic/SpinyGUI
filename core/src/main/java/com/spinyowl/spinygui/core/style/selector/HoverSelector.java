package com.spinyowl.spinygui.core.style.selector;

import com.spinyowl.spinygui.core.node.base.Node;
import com.spinyowl.spinygui.core.converter.css3.annotations.PseudoSelector;

import java.util.StringJoiner;

@PseudoSelector(":hover")
public class HoverSelector implements StyleSelector {
    @Override
    public boolean test(Node node) {
        return node.isHovered();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HoverSelector.class.getSimpleName() + "[", "]")
                .toString();
    }
}
