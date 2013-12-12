package com.pstu.acdps.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PaymentsPagePlace extends Place{
    public static final String VIEW_HISTORY_TOKEN = "payments_page";


    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<PaymentsPagePlace> {

        public PaymentsPagePlace getPlace(String token) {
            return new PaymentsPagePlace();
        }

        public String getToken(PaymentsPagePlace place) {
            return "";
        }
    }

    public String toString() {
        return VIEW_HISTORY_TOKEN + ":" + new Tokenizer().getToken(this);
    }
}
