package com.pstu.acdps.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pstu.acdps.client.mvp.activity.MainAbstractActivity;
import com.pstu.acdps.client.mvp.place.PaymentsPagePlace;

public class PaymentsPageActivity extends MainAbstractActivity {

    private PaymentsPagePlace place;

    public PaymentsPageActivity(PaymentsPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {
    }

}
