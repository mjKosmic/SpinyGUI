package com.spinyowl.spinygui.core.api;

import com.spinyowl.spinygui.core.event.WindowCloseEvent;
import com.spinyowl.spinygui.core.event.listener.Listener;
import com.spinyowl.spinygui.core.node.base.Node;
import com.spinyowl.spinygui.core.system.service.ServiceHolder;
import org.joml.Vector2i;

import java.util.List;
import java.util.Objects;


/**
 * Window class. Represents OS window.
 * <br>
 * <br>
 * <b>If you need to add custom functionality to winodw class - you need to create proxy for instance created by static method!</b>
 */
public abstract class Window {
    /**
     * Root panel.
     */
    private volatile boolean closed = false;
    private String title;
    private final Frame frame;

    public Window() {
        frame = new Frame();
    }

    public static Window createWindow(int width, int height, String title) {
        return ServiceHolder.getWindowService().createWindow(width, height, title);
    }

    public static Window createWindow(int width, int height, String title, Monitor monitor) {
        return ServiceHolder.getWindowService().createWindow(width, height, title, monitor);
    }

    public abstract long getPointer();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
            ServiceHolder.getWindowService().setWindowTitle(this, title);
        }
    }

    public Vector2i getPosition() {
        return ServiceHolder.getWindowService().getWindowPosition(this);
    }

    public void setPosition(Vector2i position) {
        ServiceHolder.getWindowService().setWindowPosition(this, position);
    }

    public void setPosition(int x, int y) {
        setPosition(new Vector2i(x, y));
    }

    public Vector2i getSize() {
        return ServiceHolder.getWindowService().getWindowSize(this);
    }

    public void setSize(Vector2i size) {
        ServiceHolder.getWindowService().setWindowSize(this, size);
    }

    public void setSize(int width, int height) {
        this.setSize(new Vector2i(width, height));
    }

    public boolean isVisible() {
        return ServiceHolder.getWindowService().isWindowVisible(this);
    }

    public void setVisible(boolean visible) {
        ServiceHolder.getWindowService().setWindowVisible(this, visible);
    }

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        closed = ServiceHolder.getWindowService().closeWindow(this);
    }

    public abstract Monitor getMonitor();

    public abstract void setMonitor(Monitor monitor);

    public LayerContainer getContainer() {
        return frame.getContainer();
    }

    public void addWindowCloseEventListener(Listener<WindowCloseEvent> listener) {
        Objects.requireNonNull(listener);
        frame.getContainer().addWindowCloseEventListener(listener);
    }

    public void removeWindowCloseEventListener(Listener<WindowCloseEvent> listener) {
        Objects.requireNonNull(listener);
        frame.getContainer().removeWindowCloseEventListener(listener);
    }

    public List<Listener<WindowCloseEvent>> getWindowCloseEventListeners() {
        return frame.getContainer().getWindowCloseEventListeners();
    }

    public abstract Node getFocusOwner();

    public Frame getFrame() {
        return frame;
    }
}
