package com.pstu.acdps.client.components;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public class AccessDeniedWidget extends Composite {

    private HTML panel;

    public AccessDeniedWidget() {
        panel = new HTML("<h1>Доступ к этой странице запрещен,<br> обратитесь к администратору</h1>");
        panel.addStyleName("access-denied-view");
        initWidget(panel);
    }
}
