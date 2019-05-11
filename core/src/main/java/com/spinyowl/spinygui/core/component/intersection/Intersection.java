package com.spinyowl.spinygui.core.component.intersection;

import com.spinyowl.spinygui.core.component.base.Node;

/**
 * Intersection class specifies intersection rules for component and point.
 * Used to allow detect intersection of point on virtual window surface and component.
 */
public interface Intersection {

    /**
     * Intersection rule.
     *
     * @param node node to check intersection.
     * @param x         x coordinates of point to check intersection.
     * @param y         y coordinates of point to check intersection.
     * @return true if node intersected by point.
     */
    boolean intersects(Node node, float x, float y);
}
