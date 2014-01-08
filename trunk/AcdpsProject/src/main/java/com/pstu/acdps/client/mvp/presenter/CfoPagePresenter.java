package com.pstu.acdps.client.mvp.presenter;

import com.pstu.acdps.client.type.ActionType;
import com.pstu.acdps.shared.dto.CfoDto;

public interface CfoPagePresenter {
    public interface ActionHandler{
        public void doAction(ActionType actionType, CfoDto dto);
    }

    public ActionHandler getActionHandler();
    public void loadAllCfo();
}
