package com.pstu.acdps.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.pstu.acdps.client.components.AlertDialogBox;
import com.pstu.acdps.client.components.AlertDialogBox.EAlertType;
import com.pstu.acdps.client.components.LoginWidget;
import com.pstu.acdps.client.mvp.ClientFactory;
import com.pstu.acdps.client.mvp.CustomActivityMapper;
import com.pstu.acdps.client.mvp.CustomPlaceHistoryMapper;
import com.pstu.acdps.client.mvp.place.SomethingPlace;
import com.pstu.acdps.shared.dto.UserDto;

public class Site implements EntryPoint {

    public static final GwtRpcServiceAsync service = GWT.create(GwtRpcService.class);
    public static final AppMessages messages = GWT.create(AppMessages.class);
    public static Logger logger = Logger.getLogger("");
    public static final Element loadingElement = DOM.getElementById("loading");
    public static final Element waitingBlock = DOM.getElementById("waiting-block");
    public static final int STATUS_CODE_OK = 200;

    public static UserDto user;
    public static ScrollPanel contentPanel = new ScrollPanel();
    HandlerRegistration historyHandlerRegistration = null;

    public static void handleError(Throwable caught) {
        System.out.println();
        logger.log(Level.WARNING, caught.getMessage(), caught);
        AlertDialogBox.showDialogBox(new AlertDialogBox("Ошибка!", caught.getMessage(), EAlertType.ERROR));
    }

    public void onModuleLoad() {
        service.getCurrentUser(new SimpleAsyncCallback<UserDto>() {
            public void onSuccess(UserDto user) {
                initPlaceHistoryHandler(user);
            }
        });
    }

    public static void setWaitingBlockVisible(boolean visible) {
        loadingElement.getStyle().setDisplay(visible ? Display.BLOCK : Display.NONE);
    }

    private void initPlaceHistoryHandler(UserDto user) {
        Site.user = user;
        if (historyHandlerRegistration != null) historyHandlerRegistration.removeHandler();
        if (user == null) {
            GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable caught) {
                    handleError(caught);
                }

                public void onSuccess() {
                    initLoginPage();
                }
            });
        }
        else {
            GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable caught) {
                    handleError(caught);
                }

                public void onSuccess() {
                    intAuthorizedUserGUI();
                }
            });
        }
    }

    protected void initLoginPage() {
        setWaitingBlockVisible(false);
        RootPanel rootLayoutPanel = RootPanel.get("container");
        rootLayoutPanel.add(contentPanel);
        contentPanel.setStyleName("content-container text-center");
        contentPanel.setWidget(new LoginWidget());
    }

    public static void logout() {
        RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT.getHostPageBaseURL() + "/logout");
        try {
            rb.sendRequest(null, new RequestCallback() {
                public void onResponseReceived(Request request, Response response) {
                    Window.Location.reload();
                }

                public void onError(Request request, Throwable t) {
                    handleError(t);
                }
            });
        }
        catch (RequestException e) {
            handleError(e);
        }
    }

    @SuppressWarnings("deprecation")
    private void intAuthorizedUserGUI() {
        final ClientFactory clientFactory = GWT.create(ClientFactory.class);
        RootPanel rootLayoutPanel = RootPanel.get("container");
        rootLayoutPanel.add(contentPanel);
        contentPanel.setStyleName("content-container");

        ActivityMapper activityMapper = new CustomActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, clientFactory.getEventBus());
        activityManager.setDisplay(contentPanel);

        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler((PlaceHistoryMapper) GWT.create(CustomPlaceHistoryMapper.class));
        historyHandlerRegistration = historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new SomethingPlace());
        historyHandler.handleCurrentHistory();
        setWaitingBlockVisible(false);
        SSPObjectView view = new SSPObjectView("Подразделения");
        contentPanel.setWidget(view);
    }
}
