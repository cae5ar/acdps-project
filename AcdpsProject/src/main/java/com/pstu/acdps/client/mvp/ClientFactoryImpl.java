package com.pstu.acdps.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.Widget;
import com.pstu.acdps.client.components.AccessDeniedWidget;

public class ClientFactoryImpl implements ClientFactory {

    private final EventBus eventBus = new SimpleEventBus();
    private final SiteHeader portalHeader = new SiteHeaderImpl();
    private final AccessDeniedWidget accessDeniedWidget = new AccessDeniedWidget();
    private final AboutPageView aboutView = new AboutPageView();

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

    public Widget getAccessDeniedView() {
        return accessDeniedWidget;
    }

    public Widget getAboutView() {
        return aboutView;
    }

}
