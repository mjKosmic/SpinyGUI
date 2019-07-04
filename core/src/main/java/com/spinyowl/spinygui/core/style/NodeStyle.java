package com.spinyowl.spinygui.core.style;

import com.spinyowl.spinygui.core.style.types.*;
import com.spinyowl.spinygui.core.style.types.border.Border;

public class NodeStyle {

    private final Background background = new Background();
    private final Padding padding = new Padding();
    private final Margin margin = new Margin();

    private final Border border = new Border();
    private final BorderRadius borderRadius = new BorderRadius();

    private Length width;
    private Length height;

    private Length minWidth;
    private Length minHeight;

    private Length maxWidth;
    private Length maxHeight;

    private Display display = Display.BLOCK;
    private Position position = Position.RELATIVE;

    private Length top;
    private Length bottom;
    private Length right;
    private Length left;

    public BorderRadius getBorderRadius() {
        return borderRadius;
    }

    public Length getWidth() {
        return width;
    }

    public void setWidth(Length width) {
        this.width = width;
    }

    public Length getHeight() {
        return height;
    }

    public void setHeight(Length height) {
        this.height = height;
    }

    public Length getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(Length minWidth) {
        this.minWidth = minWidth;
    }

    public Length getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Length minHeight) {
        this.minHeight = minHeight;
    }

    public Length getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(Length maxWidth) {
        this.maxWidth = maxWidth;
    }

    public Length getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Length maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Padding getPadding() {
        return padding;
    }

    public Margin getMargin() {
        return margin;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Border getBorder() {
        return border;
    }

    public Length getTop() {
        return top;
    }

    public void setTop(Length top) {
        this.top = top;
    }

    public Length getBottom() {
        return bottom;
    }

    public void setBottom(Length bottom) {
        this.bottom = bottom;
    }

    public Length getRight() {
        return right;
    }

    public void setRight(Length right) {
        this.right = right;
    }

    public Length getLeft() {
        return left;
    }

    public void setLeft(Length left) {
        this.left = left;
    }

    public Background getBackground() {
        return background;
    }
}
