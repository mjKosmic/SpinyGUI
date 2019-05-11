package com.spinyowl.spinygui.core.component;

import com.spinyowl.spinygui.core.component.base.EmptyNode;

public class Input extends EmptyNode {

    public String getValue() {
        return getAttribute("value");
    }

    public void setValue(String value) {
        setAttribute("value", value);
    }

    public String getName() {
        return getAttribute("name");
    }

    public void setName(String name) {
        setAttribute("name", name);
    }
}
