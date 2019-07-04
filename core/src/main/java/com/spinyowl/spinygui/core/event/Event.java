package com.spinyowl.spinygui.core.event;

import com.spinyowl.spinygui.core.node.base.Node;

public abstract class Event<T extends Node> {
    public final T target;

    public Event(T target) {
        this.target = target;
    }
}
