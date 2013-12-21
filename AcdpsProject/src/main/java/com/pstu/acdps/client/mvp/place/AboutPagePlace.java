package com.pstu.acdps.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class AboutPagePlace extends Place {
    public static final String VIEW_HISTORY_TOKEN = "about_page";

    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<AboutPagePlace> {

        public AboutPagePlace getPlace(String token) {
            return new AboutPagePlace();
        }

        public String getToken(AboutPagePlace place) {
            return "";
        }
    }

    public String toString() {
        return VIEW_HISTORY_TOKEN + ":" + new Tokenizer().getToken(this);
    }
}
