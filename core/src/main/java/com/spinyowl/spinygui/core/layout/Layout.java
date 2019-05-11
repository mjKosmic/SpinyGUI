package com.spinyowl.spinygui.core.layout;

import com.spinyowl.spinygui.core.component.base.Node;

/**
 * Layout interface.
 * Specifies rules on how to lay out child and parent components.
 */
public interface Layout {

    /**
     * Used to lay out child and parent components.
     *
     */
    void layout(Node node);
}
