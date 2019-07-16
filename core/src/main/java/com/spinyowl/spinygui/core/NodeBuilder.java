package com.spinyowl.spinygui.core;

import com.spinyowl.spinygui.core.node.*;
import com.spinyowl.spinygui.core.node.base.Node;
import com.spinyowl.spinygui.core.node.base.Text;

public final class NodeBuilder {
    private NodeBuilder() {
    }

    public static Button button() {
        return new Button();
    }

    public static Button button(Node... nodes) {
        Button button = new Button();
        for (Node node : nodes) {
            button.add(node);
        }
        return button;
    }

    public static Text text(String text) {
        return new Text(text);
    }

    public static Input input() {
        return new Input();
    }

    public static Label label() {
        return new Label();
    }
    public static Label label(Node... nodes) {
        Label label = new Label();
        for (Node node : nodes) {
            label.add(node);
        }
        return label;
    }

    public static Panel panel() {
        return new Panel();
    }

    public static Panel panel(Node... nodes) {
        Panel panel = new Panel();
        for (Node node : nodes) {
            panel.add(node);
        }
        return panel;
    }

    public static RadioButton radioButton() {
        return new RadioButton();
    }

    public static RadioButton radioButton(Node... nodes) {
        RadioButton radioButton = new RadioButton();
        for (Node node : nodes) {
            radioButton.add(node);
        }
        return radioButton;
    }
}
