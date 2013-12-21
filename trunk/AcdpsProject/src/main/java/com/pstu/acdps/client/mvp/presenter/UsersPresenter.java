package com.pstu.acdps.client.mvp.presenter;

import com.pstu.acdps.client.type.ActionType;
import com.pstu.acdps.shared.dto.UserDto;

public interface UsersPresenter {

    public interface ActionHandler{
        public void doAction(ActionType actionType, UserDto dto);
    }

    public ActionHandler getActionHandler();
    public void loadAllUsers();
}
