package com.pstu.acdps.client.components;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class CustomPasswordTextBox extends AbstractSimpleInput {

    private PasswordTextBox textBox = new PasswordTextBox();

    public CustomPasswordTextBox() {
        wrapInput(textBox);
    }

    public String getValue() {
        return textBox.getText();
    }

    public void setValue(String strValue) {
        textBox.setText(strValue);
    }

    protected void setFocus() {
        textBox.setFocus(true);
    }

    public void addInputStyleName(String style) {
        textBox.addStyleName(style);
    }

    public void setPlaceHolderText(String str) {
        textBox.getElement().setAttribute("placeholder", str);
    }

    public HandlerRegistration addKeyDownHandler(KeyDownHandler keyHandler) {
        return textBox.addKeyDownHandler(keyHandler);
    }

    public void setFocus(boolean b) {
        textBox.setFocus(b);
    }

    public void setEnabled(boolean b) {
        textBox.setEnabled(b);
    }
}
