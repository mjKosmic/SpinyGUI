package com.spinyowl.spinygui.core.node;


import com.spinyowl.spinygui.core.node.base.Container;

public class RadioButton extends Container {
    private RadioButtonGroup radioButtonGroup;

    public RadioButtonGroup getRadioButtonGroup() {
        return radioButtonGroup;
    }

    public void setRadioButtonGroup(RadioButtonGroup radioButtonGroup) {
        this.radioButtonGroup = radioButtonGroup;
    }
}
