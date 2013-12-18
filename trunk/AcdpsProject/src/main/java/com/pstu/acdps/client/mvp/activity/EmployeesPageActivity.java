package com.pstu.acdps.client.mvp.activity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.TreeItem;
import com.pstu.acdps.client.SimpleAsyncCallback;
import com.pstu.acdps.client.Site;
import com.pstu.acdps.client.components.AlertDialogBox;
import com.pstu.acdps.client.components.CustomPopup;
import com.pstu.acdps.client.components.EmployeeEditPopup;
import com.pstu.acdps.client.components.EmployeeEditPopup.EmployeeSaveHandler;
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
    private Map<Long, String> jobMap;
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
        EmployeeEditPopup popup = new EmployeeEditPopup(new EmployeeSaveHandler() {
            public void save(JobPosDto dto, final CustomPopup sender) {
                Site.service.saveEmployee(dto, new SimpleAsyncCallback<Long>() {
                    public void onSuccess(Long result) {
                        AlertDialogBox.showDialogBox("Изменения успешно сохранены");
                        sender.hide();
                        view.reset();
                    }
                });
            }
        }, dto, tree, jobMap);
        popup.show();
    }

    public void start(final AcceptsOneWidget container, EventBus eventBus) {
        Site.service.getAllJob(new SimpleAsyncCallback<Map<Long, String>>() {
            public void onSuccess(Map<Long, String> result) {
                jobMap = result;
                view = new EmployeesView(EmployeesPageActivity.this);
                container.setWidget(view);
            }
        });
    }

    public ActionHandler getActionHandler() {
        return actionHanlder;
    }

    public void loadAllEmployyes() {
        Site.service.getAllEmployees(new SimpleAsyncCallback<List<JobPosDto>>() {
            public void onSuccess(List<JobPosDto> result) {
                view.setItems(result);
            }
        });
    }

    public Map<Long, String> getAllJobs() {
        return jobMap;
    }

}
