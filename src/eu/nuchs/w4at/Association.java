package eu.nuchs.w4at;

class Association {

    Association(String aCharacter, String anAssociate, String aLocation) {

        if (isNullOrWhiteSpace(aCharacter)) {
            throw new IllegalArgumentException("aCharacter");
        }

        if (isNullOrWhiteSpace(anAssociate)) {
            throw new IllegalArgumentException("anAssociate");
        }

        if (isNullOrWhiteSpace(aLocation)) {
            throw new IllegalArgumentException("aLocation");
        }

        character = aCharacter;
        associate = anAssociate;
        location = aLocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Association)) {
            return false;
        }

        Association other = (Association) obj;
        return character.equals(other.character) &&
                associate.equals(other.associate) &&
                location.equals(other.location);
    }

    @Override
    public int hashCode() {
        return (3 * character.hashCode()) +
                (5 * associate.hashCode()) +
                (7 * location.hashCode());
    }

    @Override
    public String toString() {
        return character + " met " + associate + " at " + location;
    }

    String getCharacter() {
        return character;
    }

    String getAssociate() {
        return associate;
    }

    String getLocation() {
        return location;
    }

    private boolean isNullOrWhiteSpace(String word) {
        return word == null || word.trim().isEmpty();
    }

    private final String character;
    private final String associate;
    private final String location;

}
