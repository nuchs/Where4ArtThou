package eu.nuchs.w4at;

import com.google.gson.Gson;

import java.util.*;

class Character {

    Character(String characterName) {

        if (isNullOrWhiteSpace(characterName)) {
            throw new IllegalArgumentException("You must provide a name for a character");
        }

        name = characterName;
    }

    void addAssociate(String associate, String location) {
        if (!associate.equals(name)) {
            associates.add(associate);
            meetings.add(location, associate);
        }
    }

    String toJson() {
        return gson.toJson(this);
    }

    private boolean isNullOrWhiteSpace(String characterName) {
        return characterName == null || characterName.trim().isEmpty();
    }

    private static Gson gson = new Gson();
    private String name;
    private Set<String> associates = new HashSet<>();
    private Meetings meetings = new Meetings();
}
