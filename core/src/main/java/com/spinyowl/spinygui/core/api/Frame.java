package com.spinyowl.spinygui.core.api;

import com.spinyowl.spinygui.core.component.Panel;
import com.spinyowl.spinygui.core.component.base.Container;

public class Frame {

    private Container container = new Panel();

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
