package com.pstu.acdps.client.mvp.presenter;

import java.util.Map;

import com.pstu.acdps.shared.dto.JobPosDto;

public interface EmployeesPresenter {
    public interface ActionHandler{
        public void doAction(ActionType actionType, JobPosDto dto);
    }
    
    public enum ActionType {
        EDIT,
        REMOVE;
    }
    
    
    public ActionHandler getActionHandler();
    public void loadAllEmployyes();
    public Map<Long,String> getAllJobs();
}
