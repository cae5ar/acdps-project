package com.pstu.acdps.client.components;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.TextBox;

public class CustomTextBox extends AbstractSimpleInput {

    private TextBox textBox = new TextBox();

    public CustomTextBox() {
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

    @Override
    public void addInputStyleName(String style) {
        textBox.addStyleName(style);
    }

    public void setPlaceHolderText(String string) {
        textBox.getElement().setAttribute("placeholder", string);
    }

    public void setEnabled(boolean b) {
        textBox.setEnabled(b);
    }

    public void addKeyDownHandler(KeyDownHandler keyHandler) {
        textBox.addKeyDownHandler(keyHandler);
    }

    public TextBox getTextBox() {
        return textBox;
    }

}
