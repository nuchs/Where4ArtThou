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

    void addAssociate(String associate) {
        if (!associates.contains(associate) && !associate.equals(name)) {
            associates.add(associate);
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
    private List<String> associates = new ArrayList<String>();
}
