package com.pstu.acdps.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.pstu.acdps.client.mvp.place.DepartmentsPagePlace;
import com.pstu.acdps.client.mvp.place.EmployeesPagePlace;
import com.pstu.acdps.client.mvp.place.PaymentsPagePlace;

@WithTokenizers({DepartmentsPagePlace.Tokenizer.class, EmployeesPagePlace.Tokenizer.class,
        PaymentsPagePlace.Tokenizer.class})
public interface CustomPlaceHistoryMapper extends PlaceHistoryMapper {}
