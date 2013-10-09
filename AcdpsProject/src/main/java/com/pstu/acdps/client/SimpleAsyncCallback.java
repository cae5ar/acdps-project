package com.pstu.acdps.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class SimpleAsyncCallback<T> implements AsyncCallback<T> {
    public void onFailure(Throwable caught) {
        Site.handleError(caught);
    }
}
