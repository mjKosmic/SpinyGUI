package com.spinyowl.spinygui.core.event;

import com.spinyowl.spinygui.core.node.base.Node;

public abstract class NodeEvent extends Event<Node>{
    public NodeEvent(Node target) {
        super(target);
    }
}
