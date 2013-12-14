package com.pstu.acdps.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.pstu.acdps.client.mvp.place.CfoPagePlace;
import com.pstu.acdps.client.mvp.place.DepartmentsPagePlace;
import com.pstu.acdps.client.mvp.place.EmployeesPagePlace;
import com.pstu.acdps.client.mvp.place.EstimatePagePlace;
import com.pstu.acdps.client.mvp.place.PaymentsPagePlace;
import com.pstu.acdps.client.mvp.place.ReportPagePlace;
import com.pstu.acdps.client.mvp.place.SectionPagePlace;

//@formatter:off
@WithTokenizers({DepartmentsPagePlace.Tokenizer.class, EmployeesPagePlace.Tokenizer.class,
                    PaymentsPagePlace.Tokenizer.class, EstimatePagePlace.Tokenizer.class, 
                    SectionPagePlace.Tokenizer.class, CfoPagePlace.Tokenizer.class, 
                    ReportPagePlace.Tokenizer.class})
//@formatter:on
public interface CustomPlaceHistoryMapper extends PlaceHistoryMapper {}
