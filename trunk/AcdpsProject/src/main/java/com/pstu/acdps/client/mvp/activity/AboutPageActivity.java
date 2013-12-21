package com.pstu.acdps.client.mvp.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pstu.acdps.client.mvp.ClientFactory;
import com.pstu.acdps.client.mvp.place.AboutPagePlace;

public class AboutPageActivity extends MainAbstractActivity {

    @SuppressWarnings("unused")
    private AboutPagePlace place;
    private ClientFactory clientFactory;

    public AboutPageActivity(AboutPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {
        container.setWidget(clientFactory.getAboutView());
    }

}

/*





*/