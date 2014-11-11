package eu.nuchs.w4at;

import java.util.*;

class NameMatcher {

    void addName(String name) {
        if (isNullOrWhiteSpace(name)) {
            throw new IllegalArgumentException("Name must not be null or whitespace");
        }

        names.add(name.toLowerCase());
    }

    String match(String pattern) {
        if (isNullOrWhiteSpace(pattern)) {
            return "";
        }

        String normalisedPattern = pattern.toLowerCase();
        return findBestMatch(normalisedPattern);
    }

    String findBestMatch(String pattern) {
        List<String> matches = new ArrayList<String>();

        for (String name : names) {
            if (isMatch(pattern, name)) {
                matches.add(name);
            }
        }

        return getResult(matches);
    }

    private boolean isNullOrWhiteSpace(String name) {
        return name == null || name.trim().isEmpty();
    }

    private boolean isMatch(String pattern, String nameToMatch) {
        char[] name = nameToMatch.toCharArray();
        int index = 0;

        for (char letter : pattern.toCharArray()) {
            while (letter != name[index]) {
                index++;

                if (index >= name.length) {
                    return false;
                }
            }
        }

        return true;
    }

    private String getResult(List<String> matches) {
        matches.sort(lengthComparison);
        return matches.size() > 0 ? matches.get(0) : "";
    }

    private Comparator<String> lengthComparison = new Comparator<String>() {
        @Override
        public int compare(String firstName, String secondName) {
            return firstName.length() - secondName.length();
        }
    };

    private List<String> names = new ArrayList<String>();
}
