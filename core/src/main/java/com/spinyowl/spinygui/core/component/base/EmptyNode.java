package com.spinyowl.spinygui.core.component.base;

import com.google.common.base.Objects;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class EmptyNode extends Node {

    private Map<String, String> attributes = new ConcurrentHashMap<>();

    @Override
    public List<Node> getChildNodes() {
        return Collections.emptyList();
    }

    /**
     * Returns unmodifiable collection of node attributes.
     *
     * @return unmodifiable collection of node attributes.
     */
    @Override
    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    /**
     * Used to set attribute.
     *
     * @param key   attribute name.
     * @param value attribute value.
     */
    @Override
    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    /**
     * Used to get attribute.
     *
     * @param key attribute name.
     * @return attribute value.
     */
    @Override
    public String getAttribute(String key) {
        return attributes.get(key);
    }

    /**
     * Used to remove attribute.
     *
     * @param key attribute name.
     */
    @Override
    public void removeAttribute(String key) {
        attributes.remove(key);
    }

    public String getClassAttribute() {
        return getAttribute("class");
    }

    public void setClassAttribute(String classAttribute) {
        setAttribute("class", classAttribute);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setId(String id) {
        setAttribute("id", id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmptyNode container = (EmptyNode) o;
        return Objects.equal(attributes, container.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(attributes);
    }

    /**
     * Child operations are not supported for {@link EmptyNode}.
     *
     * @param node node.
     * @throws UnsupportedOperationException because child operations are not supported for {@link EmptyNode}.
     */
    @Override
    public final void removeChild(Node node) {
        throw new UnsupportedOperationException("Child operations are not supported for EmptyNode.");
    }

    /**
     * Child operations are not supported for {@link EmptyNode}.
     *
     * @param node node.
     * @throws UnsupportedOperationException because child operations are not supported for {@link EmptyNode}.
     */
    @Override
    public final void addChild(Node node) {
        throw new UnsupportedOperationException("Child operations are not supported for EmptyNode.");
    }

}
