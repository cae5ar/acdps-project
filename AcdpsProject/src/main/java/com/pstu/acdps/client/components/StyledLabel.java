package com.pstu.acdps.client.components;

import com.google.gwt.user.client.ui.Label;

public class StyledLabel extends Label {

    public StyledLabel() {
        this("", "");
    }

    public StyledLabel(String text) {
        this(text, "");
        setText(text);
    }

    public StyledLabel(String text, String styleName) {
        setStyleName(styleName);
        setText(text);
    }

}
