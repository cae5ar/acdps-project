package com.pstu.acdps.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pstu.acdps.shared.dto.UserDto;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GwtRpcServiceAsync {
    void getCurrentUser(AsyncCallback<UserDto> callback);
}
