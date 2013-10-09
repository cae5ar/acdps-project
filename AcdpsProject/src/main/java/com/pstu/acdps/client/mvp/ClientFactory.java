package com.pstu.acdps.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.pstu.acdps.client.components.NavigationBar;

public interface ClientFactory {

    EventBus getEventBus();

    PlaceController getPlaceController();

    NavigationBar getNavigationBar();

    PortalHeader getHeader();

}
