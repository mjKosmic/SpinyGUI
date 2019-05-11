package com.spinyowl.spinygui.core.component.base;


import com.spinyowl.spinygui.core.component.intersection.Intersection;
import com.spinyowl.spinygui.core.component.intersection.Intersections;
import com.spinyowl.spinygui.core.event.EventTarget;
import com.spinyowl.spinygui.core.system.render.Renderer;
import com.spinyowl.spinygui.core.system.service.ServiceHolder;
import org.joml.Vector2f;
import org.joml.Vector2fc;

import java.util.List;
import java.util.Map;

/**
 * Base structure of any component.
 * <p>
 * Have three base subclasses that should be used to create any kind of element:
 * <ul>
 * <li>{@link Container}<br> - base for components that could contain other components. </li>
 * <li>{@link EmptyNode}<br> - base for components that could not contain other components. </li>
 * <li>{@link Text}<br> - representation of text node. </li>
 * </ul>
 */
public abstract class Node implements EventTarget {

    /**
     * Parent component.
     */
    private Node parent;

    /**
     * Node position. Mostly assigned to component by layout manager.
     */
    private Vector2f position = new Vector2f();

    /**
     * Node size. Mostly assigned to component by layout manager.
     */
    private Vector2f size = new Vector2f();

    /**
     * Node visibility.
     */
    private boolean visible;
    /**
     * Determines whether this component hovered or not (cursor is over this component).
     */
    private boolean hovered;
    /**
     * Determines whether this component focused or not.
     */
    private boolean focused;
    /**
     * Determines whether this component pressed or not (Mouse button is down and on this component).
     */
    private boolean pressed;
    /**
     * Node intersection. During initialization used {@link Intersections#getDefaultIntersection()}.
     * Used to allow detect intersection of point on virtual window surface and component.
     */
    private Intersection intersection = Intersections.getDefaultIntersection();
    /**
     * Node renderer instance.
     */
    private Renderer<? extends Node> renderer = ServiceHolder.getRendererFactoryService().getRenderer(this.getClass());

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    /**
     * Returns renderer instance for this component.
     *
     * @return renderer instance.
     */
    public Renderer getRenderer() {
        return renderer;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Returns intersection instance.
     *
     * @return intersection instance.
     */
    public Intersection getIntersection() {
        return intersection;
    }

    /**
     * Used to set intersection.
     * If intersection instance is null - intersection will be replaced with default intersection.
     *
     * @param intersection intersection to set.
     */
    public void setIntersection(Intersection intersection) {
        if (intersection != null) {
            this.intersection = intersection;
        } else {
            this.intersection = Intersections.getDefaultIntersection();
        }
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        if (parent == this) return;
        if (parent == null) throw new NullPointerException("Parent node could not be null.");

        if (this.parent != null) this.parent.removeChild(this);
        this.parent = parent;
        parent.addChild(this);
    }

    public Vector2fc getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        if (position != null) {
            this.position.set(position);
        } else {
            this.position.set(0, 0);
        }
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public Vector2fc getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        if (size != null) {
            this.size.set(size);
        } else {
            this.size.set(0, 0);
        }
    }

    public void setSize(float width, float height) {
        this.size.set(width, height);
    }

    public abstract void removeChild(Node node);

    public abstract void addChild(Node node);

    /**
     * Add method that calls {@link #addChild(Node)} method and returns {@literal this}.
     *
     * @param node node to add.
     * @return this.
     */
    public final Node add(Node node) {
        this.addChild(node);
        return this;
    }

    /**
     * Remove method that calls {@link #addChild(Node)} method and returns {@literal this}.
     *
     * @param node node to add.
     * @return this.
     */
    public final Node remove(Node node) {
        this.removeChild(node);
        return this;
    }

    public abstract List<Node> getChildNodes();

    /**
     * Returns unmodifiable collection of node attributes.
     *
     * @return unmodifiable collection of node attributes.
     */
    public abstract Map<String, String> getAttributes();

    /**
     * Used to set attribute.
     *
     * @param key   attribute name.
     * @param value attribute value.
     */
    public abstract void setAttribute(String key, String value);

    /**
     * Used to get attribute.
     *
     * @param key attribute name.
     * @return attribute value.
     */
    public abstract String getAttribute(String key);

    /**
     * Used to remove attribute.
     *
     * @param key attribute name.
     */
    public abstract void removeAttribute(String key);

    public boolean intersects(Vector2f point) {
        return intersection.intersects(this, point.x, point.y);
    }


}
