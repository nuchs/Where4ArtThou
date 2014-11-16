package eu.nuchs.w4at;

import java.util.*;

public class Meetings {

    public void add(String location, String character) {
        Set<String> characters = getCharacterSet(location);
        characters.add(character);
    }

    private Set<String> getCharacterSet(String location) {
        Set<String> characters = locations.get(location);

        if (characters == null) {
            characters = new HashSet<>();
            locations.put(location, characters);
        }

        return characters;
    }

    private Map<String, Set<String>> locations = new HashMap<>();
}
