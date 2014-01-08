package com.pstu.acdps.client.mvp.activity;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pstu.acdps.client.SimpleAsyncCallback;
import com.pstu.acdps.client.Site;
import com.pstu.acdps.client.components.AlertDialogBox;
import com.pstu.acdps.client.components.CustomPopup;
import com.pstu.acdps.client.components.UserEditPopup;
import com.pstu.acdps.client.components.AlertDialogBox.EAlertType;
import com.pstu.acdps.client.components.UserEditPopup.UserSaveHandler;
import com.pstu.acdps.client.mvp.ClientFactory;
import com.pstu.acdps.client.mvp.place.UserPagePlace;
import com.pstu.acdps.client.mvp.presenter.UsersPresenter;
import com.pstu.acdps.client.mvp.view.UsersPageView;
import com.pstu.acdps.client.type.ActionType;
import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.dto.UserDto;

public class UserPageActivity extends MainAbstractActivity implements UsersPresenter {

    @SuppressWarnings("unused")
    private UserPagePlace place;
    private UsersPageView view;
    private List<EmployeeDto> employees;

    public UserPageActivity(UserPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(final AcceptsOneWidget container, EventBus eventBus) {
        if (!Site.user.getAdmin()) {
            container.setWidget(clientFactory.getAccessDeniedView());
        }
        else {
            Site.service.getAllEmployees(new SimpleAsyncCallback<List<EmployeeDto>>() {
                public void onSuccess(List<EmployeeDto> result) {
                    setEmployees(result);
                    view = new UsersPageView(UserPageActivity.this);
                    container.setWidget(view);
                }
            });
        }
    }

    @Override
    public ActionHandler getActionHandler() {
        return new ActionHandler() {
            @Override
            public void doAction(ActionType actionType, UserDto dto) {
                switch (actionType) {
                    case EDIT:
                        editUser(dto);
                        break;
                    case REMOVE:
                        removeUser(dto.getId());
                        break;
                }
            }
        };
    }

    protected void editUser(UserDto dto) {
        if (dto == null) {
            dto = new UserDto();
        }
        UserEditPopup popup = new UserEditPopup(new UserSaveHandler() {
            public void save(UserDto dto, String password, final CustomPopup sender) {
                Site.service.saveUser(dto, password, new SimpleAsyncCallback<Long>() {
                    public void onSuccess(Long result) {
                        AlertDialogBox.showDialogBox("Изменения успешно сохранены", EAlertType.SUCCESS);
                        sender.hide();
                        view.reset();
                    }
                });
            }
        }, dto, employees);
        popup.show();
    }

    protected void removeUser(Long id) {
        Site.service.removeUser(id, new SimpleAsyncCallback<Void>() {
            public void onSuccess(Void result) {
                AlertDialogBox.showDialogBox("Изменения успешно сохранены", EAlertType.SUCCESS);
                view.reset();
            }
        });
    }

    @Override
    public void loadAllUsers() {
        Site.service.getAllUsers(new SimpleAsyncCallback<List<UserDto>>() {
            public void onSuccess(List<UserDto> result) {
                view.setItems(result);
            }
        });
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }

}
