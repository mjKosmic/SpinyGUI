package com.spinyowl.spinygui.core.layout;

import com.spinyowl.spinygui.core.api.Window;
import com.spinyowl.spinygui.core.node.base.Node;
import com.spinyowl.spinygui.core.style.types.Display;

/**
 * Layout manager. Used to layout node and it's child components.
 */
public abstract class LayoutManager {

    /**
     * Used to register layout for specified display type.
     *
     * @param displayType display type.
     * @param layout layout to register.
     */
    public abstract void registerLayout(Display displayType, Layout layout);

    /**
     * Used to layout frame layers and all of their child components.
     *
     * @param frame frame to lay out.
     */
    public abstract void layout(Window frame);

    /**
     * Used to layout node and all of his child components.
     *
     * @param node node to lay out.
     */
    public abstract void layout(Node node);
}
