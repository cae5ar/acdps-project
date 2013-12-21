package com.pstu.acdps.client.mvp.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pstu.acdps.client.Site;
import com.pstu.acdps.client.mvp.ClientFactory;
import com.pstu.acdps.client.mvp.place.ReportPagePlace;
import com.pstu.acdps.shared.type.SystemConstants;

public class ReportPageActivity extends MainAbstractActivity {

    @SuppressWarnings("unused")
    private ReportPagePlace place;

    public ReportPageActivity(ReportPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {
        if (!Site.hasUserRole(SystemConstants.roleReportIdent)) {
            container.setWidget(clientFactory.getAccessDeniedView());
        }
        else {}
    }

}
