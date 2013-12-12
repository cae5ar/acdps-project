package com.pstu.acdps.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactoryImpl implements ClientFactory {

    private final EventBus eventBus = new SimpleEventBus();
    private final SiteHeader portalHeader = new SiteHeaderImpl();

    @SuppressWarnings("deprecation")
    private final PlaceController placeController = new PlaceController(eventBus);

    public EventBus getEventBus() {
        return eventBus;
    }

    public PlaceController getPlaceController() {
        return placeController;
    }

    public SiteHeader getHeader() {
        return portalHeader;
    }

}
