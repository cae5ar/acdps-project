package com.pstu.acdps.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {

    EventBus getEventBus();

    PlaceController getPlaceController();

    SiteHeader getHeader();

}
