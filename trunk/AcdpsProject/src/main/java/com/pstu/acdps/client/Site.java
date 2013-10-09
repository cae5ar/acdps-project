package com.pstu.acdps.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.pstu.acdps.client.components.CustomDialogBox;
import com.pstu.acdps.client.components.CustomDialogBox.EAlertType;

public class Site implements EntryPoint {

    public static final GwtRpcServiceAsync service = GWT.create(GwtRpcService.class);
    public static final AppMessages messages = GWT.create(AppMessages.class);
    public static Logger logger = Logger.getLogger("");
    public static final Element loadingElement = DOM.getElementById("loading");
    public static final Element waitingBlock = DOM.getElementById("loading");
    public static final int STATUS_CODE_OK = 200;

    public static ScrollPanel contentPanel = new ScrollPanel();
    HandlerRegistration historyHandlerRegistration = null;

    public static void handleError(Throwable caught) {
        System.out.println();
        logger.log(Level.WARNING, caught.getMessage(), caught);
        CustomDialogBox.showDialogBox(new CustomDialogBox("Ошибка!", caught.getMessage(), EAlertType.ERROR));
    }

    public void onModuleLoad() {
         setWaitingBlockVisible(false);
         initPlaceHistoryHandler();
    }

    public static void setWaitingBlockVisible(boolean visible) {
        waitingBlock.getStyle().setDisplay(visible ? Display.BLOCK : Display.NONE);
    }

    private void initPlaceHistoryHandler() {
        logger.log(Level.INFO, "Create ClientFactory");
        RootPanel.get().add(contentPanel);
        contentPanel.addStyleName("container");
        contentPanel.add(new HTML("<h1>ЗДЕСЬ МОГЛА БЫТЬ ВАША РЕКЛАМА</h1>"));
        logger.log(Level.INFO, "Create mapper");
        logger.log(Level.INFO, "Redirect to welcome view");

    }
}
