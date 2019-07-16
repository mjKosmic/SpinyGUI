package com.spinyowl.spinygui.core.layout;

import com.spinyowl.spinygui.core.api.Frame;
import com.spinyowl.spinygui.core.api.Window;
import com.spinyowl.spinygui.core.layout.impl.LayoutManagerImpl;
import com.spinyowl.spinygui.core.node.base.Node;
import com.spinyowl.spinygui.core.style.types.Display;

import java.util.Objects;

/**
 * Layout manager. Used to layout node and it's child components.
 */
public abstract class LayoutManager {

    public static LayoutManager getInstance() {
        return LMH.INSTANCE;
    }

    public static void setInstance(LayoutManager instance) {
        Objects.requireNonNull(instance);
        LMH.INSTANCE = instance;
    }

    /**
     * Used to register layout for specified display type.
     *
     * @param displayType display type.
     * @param layout      layout to register.
     */
    public abstract void registerLayout(Display displayType, Layout layout);

    /**
     * Used to layout frame layers and all of their child components.
     *
     * @param frame frame to lay out.
     */
    public abstract void layout(Window frame);

    /**
     * Used to layout frame layers and all of their child components.
     *
     * @param frame frame to lay out.
     */
    public abstract void layout(Frame frame);

    /**
     * Used to layout node and all of his child components.
     *
     * @param node node to lay out.
     */
    public abstract void layout(Node node);

    /**
     * Instance holder.
     */
    private static class LMH {
        private static LayoutManager INSTANCE = new LayoutManagerImpl();
    }
}
