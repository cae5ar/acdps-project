package com.pstu.acdps.client.components;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

public class AbstractSPPObjectEditWidget extends Composite {
   protected FlowPanel panel = new FlowPanel();

    public AbstractSPPObjectEditWidget() {
        initWidget(panel);
        
        panel.addStyleName("ssp-object-widget");
    }

}
