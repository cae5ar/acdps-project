package com.pstu.acdps.client.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.pstu.acdps.client.Site;

public class LoginWidget extends FlowPanel {
    CustomTextBox loginInput = new CustomTextBox();
    CustomPasswordTextBox passwordInput = new CustomPasswordTextBox();
    private Btn submitButton = new Btn(Site.messages.msgEnter());
    private StyledLabel loginMessage = new StyledLabel("", "login-message");

    private KeyDownHandler keyHandler = new KeyDownHandler() {
        public void onKeyDown(KeyDownEvent event) {
            if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) sendCredentials();
        }
    };

    private ClickHandler clickHandler = new ClickHandler() {
        public void onClick(ClickEvent event) {
            sendCredentials();
        }
    };

    public LoginWidget() {
        super();
        addStyleName("login-view");
        FlowPanel inputsPanel = new FlowPanel();
        inputsPanel.addStyleName("inputs-block");
        add(inputsPanel);

        inputsPanel.add(loginMessage);

        loginInput.addStyleName("user-data-input");
        loginInput.setPlaceHolderText("Логин");
        inputsPanel.add(loginInput);

        passwordInput.addStyleName("user-data-input");
        passwordInput.setPlaceHolderText("Пароль");
        inputsPanel.add(passwordInput);

        submitButton.addClickHandler(clickHandler);
        passwordInput.addKeyDownHandler(keyHandler);
        loginInput.addKeyDownHandler(keyHandler);

        FlowPanel buttonPanel = new FlowPanel();
        buttonPanel.addStyleName("modal-footer login-button-panel");
        submitButton.addStyleName("login-button");
        buttonPanel.add(submitButton);

        inputsPanel.add(buttonPanel);
    }

    protected void onLoad() {
        super.onLoad();
        clearInputs();
        loginInput.setFocus();
        Element el = this.getElement();
        while (el != null) {
            el.setScrollTop(0);
            el = el.getParentElement();
        }
    }

    public String getLoginText() {
        return loginInput.getValue();
    }

    public String getPasswordText() {
        return passwordInput.getValue();
    }

    public void setLoginMessage(String message) {
        loginMessage.setText(message);
    }

    public void clearInputs() {
        loginInput.setEnabled(true);
        passwordInput.setEnabled(true);
        submitButton.setEnabled(true);
        loginInput.setValue("");
        passwordInput.setValue("");
        loginMessage.setText("");
        loginMessage.setStyleName("error", false);
    }

    public void showCheckingMessage() {
        loginMessage.setText("Проверка...");
        loginMessage.setStyleName("error", false);
        loginInput.setEnabled(false);
        passwordInput.setEnabled(false);
        submitButton.setEnabled(false);
    }

    public void sendCredentials() {
        showCheckingMessage();

        String dataToSend = URL.encode("j_username=" + getLoginText() + "&j_password=" + getPasswordText());

        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(GWT.getHostPageBaseURL()
                + "j_spring_security_check"));
        builder.setHeader("Accept-Charset", "windows-1251");
        builder.setHeader("Accept-Language", "ru, en");
        builder.setHeader("Connection", "close");
        builder.setHeader("Content-length", dataToSend.length() + "");
        builder.setHeader("Content-type", "application/x-www-form-urlencoded");

        try {
            builder.sendRequest(dataToSend, new RequestCallback() {
                public void onResponseReceived(Request request, Response response) {
                    if (response.getStatusCode() == 200 || response.getStatusCode() == 3000
                            || response.getStatusCode() == 400) {
                        Window.Location.reload();
                    }
                    else if (response.getStatusCode() == 401) {
                        clearInputs();
                        if (response.getText().contains("Authentication Failed: Maximum sessions of 1 for this principal exceeded"))
                            setLoginMessage("Такой пользователь уже в системе");
                        if (response.getText().contains("Authentication Failed: Bad credentials"))
                            setLoginMessage("Неверный логин или пароль");
                        else {
                            setLoginMessage(response.getStatusText());
                        }
                    }
                    else {
                        clearInputs();
                        setLoginMessage("Сервер недоступен");
                    }

                }

                public void onError(Request request, Throwable exception) {
                    setLoginMessage(exception.getMessage());
                }
            });
        }
        catch (Exception e) {
            setLoginMessage(e.getMessage());
        }
    }
}
