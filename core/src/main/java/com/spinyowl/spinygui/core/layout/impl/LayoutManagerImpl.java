package com.spinyowl.spinygui.core.layout.impl;

import com.spinyowl.spinygui.core.api.Frame;
import com.spinyowl.spinygui.core.layout.Layout;
import com.spinyowl.spinygui.core.layout.LayoutManager;
import com.spinyowl.spinygui.core.node.base.Element;
import com.spinyowl.spinygui.core.style.types.Display;
import com.spinyowl.spinygui.core.util.NodeUtilities;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class LayoutManagerImpl implements LayoutManager {

    private final Map<Display, Layout> layoutMap = new ConcurrentHashMap<>();

    public LayoutManagerImpl() {
        registerLayout(Display.NONE, new DisplayNoneLayout());
    }

    @Override
    public void registerLayout(Display displayType, Layout layout) {
        Objects.requireNonNull(displayType);
        Objects.requireNonNull(layout);

        layoutMap.put(displayType, layout);
    }

    @Override
    public void layout(Frame frame) {
        frame.getAllLayers().forEach(this::layout);
    }

    @Override
    public void layout(Element element) {
        if (element != null && element.isVisible()
                && NodeUtilities.visibleInParents(element)
        ) {
            Layout layout = layoutMap.get(element.getStyle().getDisplay());
            if (layout != null) {
                layout.layout(element);
            }

            element.getChildElements().forEach(this::layout);
        }
    }

}
