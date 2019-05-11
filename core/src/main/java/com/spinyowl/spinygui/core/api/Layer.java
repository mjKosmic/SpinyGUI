package com.spinyowl.spinygui.core.api;

import com.spinyowl.spinygui.core.component.base.Node;
import com.spinyowl.spinygui.core.component.intersection.Intersection;
import org.joml.Vector2f;
import org.joml.Vector2fc;

import java.util.List;

public class Layer {

    /**
     * Used to hold all components of layer.
     */
    protected LayerContainer container = new LayerContainer();
    /**
     * Parent frame.
     */
    private Frame frame;
    /**
     * Determines if  current layer allow to pass events to bottom layer if event wasn't handled by components of this layer.
     */
    private boolean eventPassable = true;
    /**
     * Determines if current layer and all of it components can receive events.
     */
    private boolean eventReceivable = true;

    /**
     * Returns frame to which attached this layer.
     *
     * @return frame to which attached this layer.
     */
    public Frame getFrame() {
        return frame;
    }

    /**
     * Used to attach layer to frame.
     *
     * @param frame frame to attach.
     */
    protected void setFrame(Frame frame) {
        if (frame == this.frame) {
            return;
        }
        if (this.frame != null) {
            this.frame.removeLayer(this);
        }
        this.frame = frame;
        if (frame != null) {
            frame.addLayer(this);
        }
    }

    /**
     * Returns component container.
     *
     * @return component container.
     */
    public LayerContainer getContainer() {
        return container;
    }

    /**
     * Used to set custom layer container.
     *
     * @param container new layer container to set.
     */
    public void setContainer(LayerContainer container) {
        container.setSize(new Vector2f(this.container.getSize()));
        this.container = container;
    }

    /**
     * Returns true if layer is event passable.
     *
     * @return true if layer is event passable.
     */
    public boolean isEventPassable() {
        return eventPassable;
    }

    /**
     * Used to enable or disable passing events to bottom layer.
     *
     * @param eventPassable true/false to enable/disable passing events to bottom layer.
     */
    public void setEventPassable(boolean eventPassable) {
        this.eventPassable = eventPassable;
    }

    /**
     * Returns true if layer is event receivable.
     *
     * @return true if layer is event receivable.
     */
    public boolean isEventReceivable() {
        return eventReceivable;
    }

    /**
     * Used to enable or disable receiving events by layer.
     *
     * @param eventReceivable true/false to enable/disable receiving events by layer.
     */
    public void setEventReceivable(boolean eventReceivable) {
        this.eventReceivable = eventReceivable;
    }


    /**
     * Returns parent component. If returns null - current component is root component.
     *
     * @return null or parent component.
     */
    public Node getParent() {
        return container.getParent();
    }

    /**
     * Used to set parent component. By default used by containers to attach component to container. Parent component used by renderers and event listeners and
     * processors. <p> Don't use this method if you want to attach component to container. In this case use {@link Node#add(Node)} method.
     *
     * @param parent component container.
     */
    public void setParent(Node parent) {
        container.setParent(parent);
    }


    /**
     * Returns position vector. Be careful during changing this vector.
     *
     * @return position vector.
     */
    public Vector2fc getPosition() {
        return container.getPosition();
    }

    /**
     * Used to set position of component.
     *
     * @param position new position for component.
     */
    public void setPosition(Vector2f position) {
        container.setPosition(position);
    }

    /**
     * Used to set current position.
     *
     * @param x x position relative to parent component.
     * @param y y position relative to parent component.
     */
    public void setPosition(float x, float y) {
        container.setPosition(x, y);
    }

    /**
     * Returns size vector of component. So to get width you can use.
     * <pre>
     * {@code
     * Vector2f size = component.getSize();
     * float width = size.x;
     * float height = size.y;
     * }
     * </pre>
     *
     * @return size of component.
     */
    public Vector2fc getSize() {
        return container.getSize();
    }

    /**
     * Used to set size vector.
     *
     * @param size size vector.
     */
    public void setSize(Vector2f size) {
        container.setSize(size);
    }

    /**
     * Used to set size vector.
     *
     * @param width  width to set.
     * @param height height to set.
     */
    public void setSize(float width, float height) {
        container.setSize(width, height);
    }

//    /**
//     * Returns absolute component position.
//     *
//     * @return position vector.
//     */
//    public Vector2f getAbsolutePosition() {
//        return container.getAbsolutePosition();
//    }

//    /**
//     * Returns true if component enabled. By default if component enabled it receives and proceed events.
//     *
//     * @return true if component enabled. default value is {@link Boolean#TRUE}.
//     */
//    public boolean isEnabled() {
//        return container.isEnabled();
//    }

//    /**
//     * Used to enable or disable component. By default if component enabled it receives and proceed events.
//     *
//     * @param enabled flag to set.
//     */
//    public void setEnabled(boolean enabled) {
//        container.setEnabled(enabled);
//    }

    /**
     * Returns true if component visible. By default if component visible it will be rendered and will receive events.
     *
     * @return true if component visible. default value is {@link Boolean#TRUE}.
     */
    public boolean isVisible() {
        return container.isVisible();
    }

    /**
     * Used to determine if point intersects component (in screen space). This method uses component intersector.
     *
     * @param point point to check.
     * @return true if component intersected by point.
     */
    public boolean intersects(Vector2f point) {
        return container.intersects(point);
    }

    /**
     * Returns component intersector which used to check if cursor intersect component or not.
     *
     * @return intersector.
     */
    public Intersection getIntersection() {
        return container.getIntersection();
    }

    /**
     * Used to set intersector for component.
     *
     * @param intersector intersector.
     */
    public void setIntersection(Intersection intersector) {
        container.setIntersection(intersector);
    }

//    /**
//     * Returns component metadata. Storage of some temporary statements. Can be used for example by stateless renderers.
//     *
//     * @return map of objects.
//     */
//    public Map<String, Object> getMetadata() {
//        return container.getMetadata();
//    }

    /**
     * Returns true if component is hovered.
     *
     * @return true if component is hovered.
     */
    public boolean isHovered() {
        return container.isHovered();
    }

    /**
     * Used to make component hovered or not.
     *
     * @param hovered new hovered value.
     */
    public void setHovered(boolean hovered) {
        container.setHovered(hovered);
    }

    /**
     * Returns true if component is focused.
     *
     * @return true if component is focused.
     */
    public boolean isFocused() {
        return container.isFocused();
    }

    /**
     * Used to make component focused or not.
     *
     * @param focused new hovered value.
     */
    public void setFocused(boolean focused) {
        container.setFocused(focused);
    }

    /**
     * Returns true if component is pressed.
     *
     * @return true if component is pressed.
     */
    public boolean isPressed() {
        return container.isPressed();
    }

    /**
     * Used to make component pressed or not.
     *
     * @param pressed new hovered value.
     */
    public void setPressed(boolean pressed) {
        container.setPressed(pressed);
    }

    /**
     * Used to retrieve child components as {@link List}. <p> <span style="color:red">NOTE: this method returns NEW {@link List} of components</span>.
     *
     * @return list of child components.
     */
    public List<Node> getChildNodes() {
        return container.getChildNodes();
    }
}