package com.spinyowl.spinygui.core.event;

import com.spinyowl.spinygui.core.node.base.Node;

public class MouseEvent extends NodeEvent {

    // modificators
    private boolean altKey;
    private boolean ctrlKey;
    private boolean shiftKey;

    // mouse button
    private int button;

    // position
    private float x;
    private float y;

    private float offsetX;
    private float offsetY;

    private float screenX;
    private float screenY;

    private float pageX;
    private float pageY;

    private float movementX;
    private float movementY;

    public MouseEvent(Node target) {
        super(target);
    }

}
