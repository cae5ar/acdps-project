package com.pstu.acdps.client.mvp.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pstu.acdps.client.mvp.ClientFactory;
import com.pstu.acdps.client.mvp.place.SectionPagePlace;

public class SectionPageActivity extends MainAbstractActivity {

    @SuppressWarnings("unused")
    private SectionPagePlace place;

    public SectionPageActivity(SectionPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {
        // TODO Auto-generated method stub

    }

}
