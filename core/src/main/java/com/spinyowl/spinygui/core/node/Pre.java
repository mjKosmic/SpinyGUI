package com.spinyowl.spinygui.core.node;

import com.spinyowl.spinygui.core.node.base.Container;
import com.spinyowl.spinygui.core.converter.dom.RawProcessor;

/**
 *
 */
public class Pre extends Container {

    public Pre() {
        setAttribute(RawProcessor.PREFORMATTED_ATTRIBUTE, Boolean.TRUE.toString());
    }

}
