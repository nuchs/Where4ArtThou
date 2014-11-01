package eu.nuchs.w4at;

import com.google.gson.Gson;

import java.util.*;

public class Character {
    public Character(String characterName) {

        if (characterName == null || characterName.trim().isEmpty()) {
            throw new IllegalArgumentException("You must provide a name for a character");
        }

        name = characterName;
    }

    public void addAssociate(String associate) {
        associates.add(associate);
    }

    public String toJson() {
        return gson.toJson(this);
    }

    private static Gson gson = new Gson();
    private String name;
    private List<String> associates = new ArrayList<String>();
}
