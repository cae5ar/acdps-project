package com.pstu.acdps.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class EstimatePagePlace extends Place {
    public static final String VIEW_HISTORY_TOKEN = "estimate_page";

    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<EstimatePagePlace> {

        public EstimatePagePlace getPlace(String token) {
            return new EstimatePagePlace();
        }

        public String getToken(EstimatePagePlace place) {
            return "";
        }
    }

    public String toString() {
        return VIEW_HISTORY_TOKEN + ":" + new Tokenizer().getToken(this);
    }
}
