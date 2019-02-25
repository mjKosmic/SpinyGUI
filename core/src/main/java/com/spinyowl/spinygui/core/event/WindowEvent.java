package com.spinyowl.spinygui.core.event;

import com.spinyowl.spinygui.core.api.Window;

public abstract class WindowEvent extends Event<Window> {

    public WindowEvent(Window target) {
        super(target);
    }
}
