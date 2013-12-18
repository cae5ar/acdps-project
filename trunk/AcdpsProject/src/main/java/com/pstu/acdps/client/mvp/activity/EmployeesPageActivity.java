package com.pstu.acdps.client.mvp.activity;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.TreeItem;
import com.pstu.acdps.client.SimpleAsyncCallback;
import com.pstu.acdps.client.Site;
import com.pstu.acdps.client.components.EmployeeEditPopup;
import com.pstu.acdps.client.components.TreeWidget;
import com.pstu.acdps.client.components.TreeWidget.ObjectsOpenHandler;
import com.pstu.acdps.client.mvp.ClientFactory;
import com.pstu.acdps.client.mvp.place.EmployeesPagePlace;
import com.pstu.acdps.client.mvp.presenter.EmployeesPresenter;
import com.pstu.acdps.shared.dto.JobPosDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;

public class EmployeesPageActivity extends MainAbstractActivity implements EmployeesPresenter {

    @SuppressWarnings("unused")
    private EmployeesPagePlace place;
    private EmployeesView view;
    private TreeWidget<SSPObjectDto> tree = new TreeWidget<SSPObjectDto>(new ObjectsOpenHandler<SSPObjectDto>() {
        public void selected(SSPObjectDto object, final TreeItem source) {
            Site.service.getDepartmentChilds(object.getId(), new Date(), new SimpleAsyncCallback<List<SSPObjectDto>>() {
                @Override
                public void onSuccess(List<SSPObjectDto> result) {
                    tree.addItemList(result, source);
                }
            });
        }
    }, new SSPObjectDto(null, "Все подразделения", null, new Date()));
    private ActionHandler actionHanlder = new ActionHandler() {
        public void doAction(ActionType actionType, JobPosDto dto) {
            switch (actionType) {
                case EDIT:
                    editEmployee(dto);
                    break;
                case REMOVE:
                    break;
            }
        }
    };

    public EmployeesPageActivity(EmployeesPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    protected void editEmployee(JobPosDto dto) {
        if (dto == null) {
            dto = new JobPosDto();
        }
        EmployeeEditPopup popup = new EmployeeEditPopup(null, dto,tree);
        popup.show();
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {
        view = new EmployeesView(this);
        container.setWidget(view);
    }

    @Override
    public ActionHandler getActionHandler() {
        return actionHanlder;
    }

    @Override
    public void getAllEmployyes() {
        Site.service.getAllEmployees(new SimpleAsyncCallback<List<JobPosDto>>() {
            public void onSuccess(List<JobPosDto> result) {
                view.setItems(result);
            }
        });
    }

}
