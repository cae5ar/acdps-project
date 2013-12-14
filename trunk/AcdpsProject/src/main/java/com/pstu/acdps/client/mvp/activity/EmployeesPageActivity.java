package com.pstu.acdps.client.mvp.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pstu.acdps.client.mvp.ClientFactory;
import com.pstu.acdps.client.mvp.place.EmployeesPagePlace;
import com.pstu.acdps.client.mvp.presenter.EmployeesPresenter;
import com.pstu.acdps.server.dao.EmployeeDto;

public class EmployeesPageActivity extends MainAbstractActivity implements EmployeesPresenter{

    @SuppressWarnings("unused")
    private EmployeesPagePlace place;

    public EmployeesPageActivity(EmployeesPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {
        EmployeesView view = new EmployeesView(this);
        container.setWidget(view);
    }

    @Override
    public void saveEmployee(EmployeeDto bean) {
        // TODO Auto-generated method stub
    }

    @Override
    public void deleteEmployee(Long id) {
        // TODO Auto-generated method stub
    }

    @Override
    public void getAllEmployyes() {
        // TODO Auto-generated method stub
    }

}
