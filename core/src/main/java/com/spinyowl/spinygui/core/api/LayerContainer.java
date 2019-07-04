package com.spinyowl.spinygui.core.api;

import com.spinyowl.spinygui.core.event.WindowCloseEvent;
import com.spinyowl.spinygui.core.event.listener.Listener;
import com.spinyowl.spinygui.core.node.base.Container;
import org.joml.Vector2f;

import java.util.List;
import java.util.Objects;

/**
 * Layer container.
 */
public class LayerContainer extends Container {

    /**
     * Default constructor. Used to create node instance without any parameters. <p> Also if you want to make it easy to use with Json
     * marshaller/unmarshaller node should contain empty constructor.
     */
    public LayerContainer() {
        initialize();
    }

    /**
     * Constructor with position and size parameters.
     *
     * @param x      x position position in parent node.
     * @param y      y position position in parent node.
     * @param width  width of node.
     * @param height height of node.
     */
    public LayerContainer(float x, float y, float width, float height) {
        initialize();
        setPosition(x, y);
        setSize(width, height);
    }

    /**
     * Constructor with position and size parameters.
     *
     * @param position position position in parent node.
     * @param size     size of node.
     */
    public LayerContainer(Vector2f position, Vector2f size) {
        initialize();
        setPosition(position);
        setSize(size);
    }

    /**
     * Used to initialize Layer container with default background and border.
     */
    private void initialize() {
//        getListenerMap().addListener(WindowSizeEvent.class, new LayerContainerWindowSizeEventListener());
//        getStyle().getBackground().setColor(ColorConstants.transparent());
//        getStyle().setBorder(null);
//        setFocusable(false);
//        setTabFocusable(false);
//        Themes.getDefaultTheme().getThemeManager().getComponentTheme(LayerContainer.class).applyAll(this);
    }


    public void addWindowCloseEventListener(Listener<WindowCloseEvent> listener) {
        Objects.requireNonNull(listener);
        getListenersFor(WindowCloseEvent.class).add(listener);
    }


    public void removeWindowCloseEventListener(Listener<WindowCloseEvent> listener) {
        Objects.requireNonNull(listener);
        getListenersFor(WindowCloseEvent.class).remove(listener);
    }

    public List<Listener<WindowCloseEvent>> getWindowCloseEventListeners() {
        return getListenersFor(WindowCloseEvent.class);
    }

}
