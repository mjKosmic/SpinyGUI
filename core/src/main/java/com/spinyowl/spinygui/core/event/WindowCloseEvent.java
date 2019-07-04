package com.spinyowl.spinygui.core.event;

import com.spinyowl.spinygui.core.node.base.Node;

public class WindowCloseEvent extends Event<Node> {

    public final long window;

    public WindowCloseEvent(Node target, long window) {
        super(target);
        this.window = window;
    }

}
