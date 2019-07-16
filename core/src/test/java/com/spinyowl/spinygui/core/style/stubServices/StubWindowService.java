package com.spinyowl.spinygui.core.style.stubServices;

import com.spinyowl.spinygui.core.api.Monitor;
import com.spinyowl.spinygui.core.api.Window;
import com.spinyowl.spinygui.core.system.service.WindowService;
import org.joml.Vector2d;
import org.joml.Vector2i;

import java.util.List;

public class StubWindowService implements WindowService {
    @Override
    public Window createWindow(int width, int height, String title) {
        return null;
    }

    @Override
    public Window createWindow(int width, int height, String title, Monitor monitor) {
        return null;
    }

    @Override
    public boolean closeWindow(Window window) {
        return false;
    }

    @Override
    public Window getWindow(long pointer) {
        return null;
    }

    @Override
    public Vector2i getWindowSize(Window window) {
        return null;
    }

    @Override
    public void setWindowSize(Window window, Vector2i size) {
    }

    @Override
    public Vector2i getWindowPosition(Window window) {
        return null;
    }

    @Override
    public void setWindowPosition(Window window, Vector2i position) {
    }

    @Override
    public boolean isWindowVisible(Window window) {
        return false;
    }

    @Override
    public void setWindowVisible(Window window, boolean visible) {
    }

    @Override
    public void setWindowTitle(Window window, String title) {
    }

    @Override
    public Vector2d getCursorPosition(Window window) {
        return null;
    }

    @Override
    public void setCursorPosition(Window window, Vector2d position) {
    }

    @Override
    public List<Window> getWindows() {
        return null;
    }
}