package com.pstu.acdps.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class DepartmentsPagePlace extends Place {
    public static final String VIEW_HISTORY_TOKEN = "departments_page";

    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<DepartmentsPagePlace> {

        public DepartmentsPagePlace getPlace(String token) {
            return new DepartmentsPagePlace();
        }

        public String getToken(DepartmentsPagePlace place) {
            return "";
        }
    }

    public String toString() {
        return VIEW_HISTORY_TOKEN + ":" + new Tokenizer().getToken(this);
    }
}
