package com.pstu.acdps.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pstu.acdps.client.mvp.activity.MainAbstractActivity;
import com.pstu.acdps.client.mvp.place.CfoPagePlace;

public class CfoPageActivity extends MainAbstractActivity {

    @SuppressWarnings("unused")
    private CfoPagePlace place;

    public CfoPageActivity(CfoPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {
        // TODO Auto-generated method stub

    }

}
