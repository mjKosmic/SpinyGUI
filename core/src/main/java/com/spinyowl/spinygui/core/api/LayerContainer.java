package com.spinyowl.spinygui.core.api;

import com.spinyowl.spinygui.core.component.base.Container;
import org.joml.Vector2f;

/**
 * Layer container.
 */
public class LayerContainer extends Container {

    /**
     * Default constructor. Used to create component instance without any parameters. <p> Also if you want to make it easy to use with Json
     * marshaller/unmarshaller component should contain empty constructor.
     */
    public LayerContainer() {
        initialize();
    }

    /**
     * Constructor with position and size parameters.
     *
     * @param x      x position position in parent component.
     * @param y      y position position in parent component.
     * @param width  width of component.
     * @param height height of component.
     */
    public LayerContainer(float x, float y, float width, float height) {
        setPosition(x, y);
        setSize(width, height);
    }

    /**
     * Constructor with position and size parameters.
     *
     * @param position position position in parent component.
     * @param size     size of component.
     */
    public LayerContainer(Vector2f position, Vector2f size) {
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

}
