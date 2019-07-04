package com.spinyowl.spinygui.core.event.listener.impl;

import com.spinyowl.spinygui.core.api.Window;
import com.spinyowl.spinygui.core.event.WindowCloseEvent;
import com.spinyowl.spinygui.core.event.listener.Listener;
import com.spinyowl.spinygui.core.system.service.ServiceHolder;

public class DefaultWindowCloseEventListener implements Listener<WindowCloseEvent> {
    @Override
    public void process(WindowCloseEvent event) {
        Window window = ServiceHolder.getWindowService().getWindow(event.window);
        if (window != null && !window.isClosed()) window.close();
    }
}
