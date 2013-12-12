package com.pstu.acdps.client.mvp.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pstu.acdps.client.SSPObjectView;
import com.pstu.acdps.client.mvp.ClientFactory;
import com.pstu.acdps.client.mvp.place.DepartmentsPagePlace;

public class DepartmentsPageActivity extends MainAbstractActivity {

    @SuppressWarnings("unused")
    private DepartmentsPagePlace place;

    public DepartmentsPageActivity(DepartmentsPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        SSPObjectView view = new SSPObjectView("Подразделения");
        panel.setWidget(view);
    }
}
