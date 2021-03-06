package com.spinyowl.spinygui.core.style;

import com.spinyowl.spinygui.core.style.types.*;
import com.spinyowl.spinygui.core.style.types.border.Border;
import com.spinyowl.spinygui.core.style.types.flex.Flex;
import com.spinyowl.spinygui.core.style.types.length.Length;
import com.spinyowl.spinygui.core.style.types.length.Unit;

public class NodeStyle {

    private final Flex flex = new Flex();
    private final Background background = new Background();
    private final Border border = new Border();
    private final BorderRadius borderRadius = new BorderRadius();
    private final Padding padding = new Padding();
    private final Margin margin = new Margin();
    private Display display = Display.BLOCK;
    private Position position = Position.RELATIVE;
    private Color color;

    private Unit width;
    private Unit height;

    private Length minWidth;
    private Length minHeight;

    private Length maxWidth;
    private Length maxHeight;

    private Length top;
    private Length bottom;
    private Length right;
    private Length left;

    private WhiteSpace whiteSpace = WhiteSpace.NORMAL;

    public Padding getPadding() {
        return padding;
    }

    public BorderRadius getBorderRadius() {
        return borderRadius;
    }

    public Unit getWidth() {
        return width;
    }

    public void setWidth(Unit width) {
        this.width = width;
    }

    public Unit getHeight() {
        return height;
    }

    public void setHeight(Unit height) {
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

    public WhiteSpace getWhiteSpace() {
        return whiteSpace;
    }

    public void setWhiteSpace(WhiteSpace whiteSpace) {
        this.whiteSpace = whiteSpace;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (color != null) {
            this.color = color;
        }
    }

    public Margin getMargin() {
        return margin;
    }

    public Flex getFlex() {
        return flex;
    }
}
