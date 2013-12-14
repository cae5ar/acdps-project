package com.pstu.acdps.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ReportPagePlace extends Place {
    public static final String VIEW_HISTORY_TOKEN = "reports_page";

    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<ReportPagePlace> {

        public ReportPagePlace getPlace(String token) {
            return new ReportPagePlace();
        }

        public String getToken(ReportPagePlace place) {
            return "";
        }
    }

    public String toString() {
        return VIEW_HISTORY_TOKEN + ":" + new Tokenizer().getToken(this);
    }
}
