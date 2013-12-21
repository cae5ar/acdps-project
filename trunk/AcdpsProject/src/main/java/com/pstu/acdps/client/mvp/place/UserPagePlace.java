package com.pstu.acdps.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class UserPagePlace extends Place {
    public static final String VIEW_HISTORY_TOKEN = "users_page";

    @Prefix(value = VIEW_HISTORY_TOKEN)
    public static class Tokenizer implements PlaceTokenizer<UserPagePlace> {

        public UserPagePlace getPlace(String token) {
            return new UserPagePlace();
        }

        public String getToken(UserPagePlace place) {
            return "";
        }
    }

    public String toString() {
        return VIEW_HISTORY_TOKEN + ":" + new Tokenizer().getToken(this);
    }
}
