package com.pstu.acdps.client.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pstu.acdps.client.components.ENavButtons;
import com.pstu.acdps.client.mvp.ClientFactory;

public abstract class MainAbstractActivity extends AbstractActivity {

    protected ClientFactory clientFactory;
    protected ENavButtons buttonName;
    abstract public void start(AcceptsOneWidget container, EventBus eventBus);

}
