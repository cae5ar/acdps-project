package com.pstu.acdps.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class EmployeesPagePlace extends Place {
    public static final String VIEW_HISTORY_TOKEN = "employees_page";

    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<EmployeesPagePlace> {

        public EmployeesPagePlace getPlace(String token) {
            return new EmployeesPagePlace();
        }

        public String getToken(EmployeesPagePlace place) {
            return "";
        }
    }

    public String toString() {
        return VIEW_HISTORY_TOKEN + ":" + new Tokenizer().getToken(this);
    }
}
