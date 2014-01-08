package com.pstu.acdps.client.components;

import java.util.List;

import com.google.gwt.user.client.ui.ListBox;

public class CustomListBox extends AbstractSimpleInput {

    private ListBox lbox = new ListBox();

    public CustomListBox(List<String> values) {
        this();
        addValues(values);
    }

    public CustomListBox() {
        wrapInput(lbox);
        addValue("");
    }

    public String getSelectedText() {
        return lbox.getItemText(lbox.getSelectedIndex());
    }

    public String getSelectedValue() {
        return lbox.getValue(lbox.getSelectedIndex());
    }

    public void addValues(List<String> values) {
        if (values != null) {
            for (String str : values) {
                addValue(str);
            }
        }
    }

    public void addValue(String str) {
        lbox.addItem(str);
    }

    public void addValue(String str, String value) {
        lbox.addItem(str, value);
    }

    public void setInputValue(String strValue) {
        for (int i = 0; i < lbox.getItemCount(); i++) {
            if (lbox.getItemText(i).equalsIgnoreCase(strValue)) {
                lbox.setSelectedIndex(i);
                return;
            }
        }
    }

    protected void setFocus() {
        lbox.setFocus(true);
    }

    public void addInputStyleName(String style) {
        lbox.addStyleName(style);
    }

    public ListBox getNativeListBox() {
        return lbox;
    }

    public void setValue(String string) {
        for (int i = 0; i < lbox.getItemCount(); i++) {
            if (lbox.getValue(i).equalsIgnoreCase(string)) {
                lbox.setSelectedIndex(i);
                return;
            }
        }
    }

}
