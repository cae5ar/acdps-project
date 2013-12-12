package com.pstu.acdps.client.mvp;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.pstu.acdps.client.mvp.activity.DepartmentsPageActivity;
import com.pstu.acdps.client.mvp.activity.EmployeesPageActivity;
import com.pstu.acdps.client.mvp.activity.MainAbstractActivity;
import com.pstu.acdps.client.mvp.place.DepartmentsPagePlace;
import com.pstu.acdps.client.mvp.place.EmployeesPagePlace;
import com.pstu.acdps.client.mvp.place.PaymentsPagePlace;

public class CustomActivityMapper implements ActivityMapper {

    private ClientFactory clientFactory;

    public CustomActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

    public MainAbstractActivity getActivity(Place place) {
        if (place instanceof DepartmentsPagePlace) {
            return new DepartmentsPageActivity((DepartmentsPagePlace) place, clientFactory);
        }
        if (place instanceof EmployeesPagePlace) {
            return new EmployeesPageActivity((EmployeesPagePlace) place, clientFactory);
        }
        if (place instanceof PaymentsPagePlace) {
            return new PaymentsPageActivity((PaymentsPagePlace) place, clientFactory);
        }
        return null;
    }
}
