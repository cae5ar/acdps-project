package com.pstu.acdps.client.mvp.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pstu.acdps.client.Site;
import com.pstu.acdps.client.mvp.ClientFactory;
import com.pstu.acdps.client.mvp.place.PaymentsPagePlace;
import com.pstu.acdps.shared.type.SystemConstants;

public class PaymentsPageActivity extends MainAbstractActivity {

    @SuppressWarnings("unused")
    private PaymentsPagePlace place;

    public PaymentsPageActivity(PaymentsPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {
        if (!Site.hasUserRole(SystemConstants.rolePaymentIdent)) {
            container.setWidget(clientFactory.getAccessDeniedView());
        }
        else {}
    }

}
