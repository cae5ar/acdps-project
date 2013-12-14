package com.pstu.acdps.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;


public class CfoPagePlace extends Place {
    public static final String VIEW_HISTORY_TOKEN = "cfo_page";

    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<CfoPagePlace> {

        public CfoPagePlace getPlace(String token) {
            return new CfoPagePlace();
        }

        public String getToken(CfoPagePlace place) {
            return "";
        }
    }

    public String toString() {
        return VIEW_HISTORY_TOKEN + ":" + new Tokenizer().getToken(this);
    }
}
