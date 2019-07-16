package com.spinyowl.spinygui.core.system.context;

import com.spinyowl.spinygui.core.node.base.Node;
import org.joml.Vector2d;
import org.joml.Vector2dc;
import org.joml.Vector2i;
import org.joml.Vector2ic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Context {
    private final long windowPointer;
    private final Map<String, Object> contextData = new ConcurrentHashMap<>();
    public Node focusedComponent;
    private Vector2i windowSize = new Vector2i();
    private Vector2i windowPos = new Vector2i();
    private Vector2d cursorPos = new Vector2d();
    private Vector2i frameBufferSize = new Vector2i();

    public Context(long windowPointer) {
        this.windowPointer = windowPointer;
    }

    public Vector2ic getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(Vector2i windowSize) {
        this.windowSize.set(windowSize);
    }

    public void setWindowSize(int windowWidth, int windowHeight) {
        windowSize.set(windowWidth, windowHeight);
    }

    public Vector2ic getWindowPos() {
        return windowPos;
    }

    public void setWindowPos(Vector2i windowPos) {
        this.windowPos.set(windowPos);
    }

    public void setWindowPos(int windowX, int windowY) {
        windowPos.set(windowX, windowY);
    }

    public Vector2dc getCursorPos() {
        return cursorPos;
    }

    public void setCursorPos(Vector2d cursorPos) {
        this.cursorPos.set(cursorPos);
    }

    public void setCursorPos(double cursorX, double cursorY) {
        cursorPos.set(cursorX, cursorY);
    }

    public Vector2ic getFramebufferSize() {
        return frameBufferSize;
    }

    public void setFrameBufferSize(int frameBufferWidth, int frameBufferHeight) {
        frameBufferSize.set(frameBufferWidth, frameBufferHeight);
    }


    public void setFrameBufferSize(Vector2i frameBufferSize) {
        this.frameBufferSize.set(frameBufferSize);
    }


    public float getPixelRatio() {
        return (float) frameBufferSize.x / (float) windowSize.x;
    }

    public Map<String, Object> getContextData() {
        return contextData;
    }
}
