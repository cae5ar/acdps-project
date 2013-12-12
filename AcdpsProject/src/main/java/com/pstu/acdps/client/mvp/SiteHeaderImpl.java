package com.pstu.acdps.client.mvp;

import com.pstu.acdps.client.Site;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class SiteHeaderImpl implements SiteHeader {

    private JQuery logoutLink;

    public SiteHeaderImpl() {
        logoutLink = JQuery.select("#logout-link");
        logoutLink.click(new EventHandler() {
            @Override
            public void eventComplete(JQEvent event, JQuery currentJQuery) {
                Site.logout();
            }
        });
    }

    @Override
    public void setVisibleLogoutButton(boolean visible) {
        if(visible)
            logoutLink.removeClass("hide");
        else
            logoutLink.addClass("hide");
    }
}
