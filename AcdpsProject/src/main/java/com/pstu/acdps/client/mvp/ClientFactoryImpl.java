package com.pstu.acdps.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.pstu.acdps.client.components.NavigationBar;

public class ClientFactoryImpl implements ClientFactory {

    private final EventBus eventBus = new SimpleEventBus();
    private final NavigationBar navigationBar = new NavigationBar();
    private final PortalHeader portalHeader = new PortalHeader();

    @SuppressWarnings("deprecation")
    private final PlaceController placeController = new PlaceController(eventBus);

    public EventBus getEventBus() {
        return eventBus;
    }

    public PlaceController getPlaceController() {
        return placeController;
    }

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }

    public PortalHeader getHeader() {
        return portalHeader;
    }

}
