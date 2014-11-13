package eu.nuchs.w4at;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Meetings {

    public void add(String location, String character) {
        List<String> characters = getCharacterList(location);

        if (!characters.contains(character)) {
            characters.add(character);
        }
    }

    private List<String> getCharacterList(String location) {
        List<String> characters = locations.get(location);

        if (characters == null) {
            characters = new ArrayList<>();
            locations.put(location, characters);
        }

        return characters;
    }

    private Map<String, List<String>> locations = new HashMap<>();
}
