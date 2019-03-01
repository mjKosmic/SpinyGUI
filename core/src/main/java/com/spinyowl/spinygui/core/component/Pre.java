package com.spinyowl.spinygui.core.component;

import com.spinyowl.spinygui.core.component.base.Container;
import com.spinyowl.spinygui.core.converter.dom.RawProcessor;

/**
 *
 */
public class Pre extends Container {

    public Pre() {
        setAttribute(RawProcessor.PREFORMATTED_ATTRIBUTE, Boolean.TRUE.toString());
    }

}
