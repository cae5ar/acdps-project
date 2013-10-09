package com.pstu.acdps.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class CustomActivityMapper implements ActivityMapper {

    private ClientFactory clientFactory;

    public CustomActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

    public Activity getActivity(Place place) {
        
        PortalHeader header = clientFactory.getHeader();
        header.setButtonInCaption(null);
        header.setTextInCaption(null);
        return null;
    }
}
