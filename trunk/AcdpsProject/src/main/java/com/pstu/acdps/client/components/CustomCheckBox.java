package com.pstu.acdps.client.components;

import com.google.gwt.user.client.ui.CheckBox;

public class CustomCheckBox extends AbstractSimpleInput {

    private CheckBox checkBox = new CheckBox();

    public CustomCheckBox() {
        wrapInput(checkBox);
    }

    public Boolean getValue() {
        return checkBox.getValue();
    }

    public void setValue(boolean value) {
        checkBox.setValue(value);
    }

    public void setFocus() {
        checkBox.setFocus(true);
    }

    public void addInputStyleName(String style) {
        checkBox.setStyleName(style);
    }
    
    public CheckBox getNativeCheckBox(){
        return checkBox;
    }
}
