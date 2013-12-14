package com.pstu.acdps.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class SectionPagePlace extends Place {
    public static final String VIEW_HISTORY_TOKEN = "section_page";

    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<SectionPagePlace> {

        public SectionPagePlace getPlace(String token) {
            return new SectionPagePlace();
        }

        public String getToken(SectionPagePlace place) {
            return "";
        }
    }

    public String toString() {
        return VIEW_HISTORY_TOKEN + ":" + new Tokenizer().getToken(this);
    }
}
