package com.pstu.acdps.client.mvp;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.pstu.acdps.client.mvp.activity.AboutPageActivity;
import com.pstu.acdps.client.mvp.activity.DepartmentsPageActivity;
import com.pstu.acdps.client.mvp.activity.EmployeesPageActivity;
import com.pstu.acdps.client.mvp.activity.EstimatePageActivity;
import com.pstu.acdps.client.mvp.activity.MainAbstractActivity;
import com.pstu.acdps.client.mvp.activity.PaymentsPageActivity;
import com.pstu.acdps.client.mvp.activity.ReportPageActivity;
import com.pstu.acdps.client.mvp.activity.SectionPageActivity;
import com.pstu.acdps.client.mvp.activity.UserPageActivity;
import com.pstu.acdps.client.mvp.place.AboutPagePlace;
import com.pstu.acdps.client.mvp.place.CfoPagePlace;
import com.pstu.acdps.client.mvp.place.DepartmentsPagePlace;
import com.pstu.acdps.client.mvp.place.EmployeesPagePlace;
import com.pstu.acdps.client.mvp.place.EstimatePagePlace;
import com.pstu.acdps.client.mvp.place.PaymentsPagePlace;
import com.pstu.acdps.client.mvp.place.ReportPagePlace;
import com.pstu.acdps.client.mvp.place.SectionPagePlace;
import com.pstu.acdps.client.mvp.place.UserPagePlace;

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
        if (place instanceof CfoPagePlace) {
            return new CfoPageActivity((CfoPagePlace) place, clientFactory);
        }
        if (place instanceof SectionPagePlace) {
            return new SectionPageActivity((SectionPagePlace) place, clientFactory);
        }
        if (place instanceof PaymentsPagePlace) {
            return new PaymentsPageActivity((PaymentsPagePlace) place, clientFactory);
        }
        if (place instanceof EstimatePagePlace) {
            return new EstimatePageActivity((EstimatePagePlace) place, clientFactory);
        }
        if (place instanceof ReportPagePlace) {
            return new ReportPageActivity((ReportPagePlace) place, clientFactory);
        }
        if (place instanceof UserPagePlace) {
            return new UserPageActivity((UserPagePlace) place, clientFactory);
        }
        if (place instanceof AboutPagePlace) {
            return new AboutPageActivity((AboutPagePlace) place, clientFactory);
        }
        return null;
    }
}
