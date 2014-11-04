package eu.nuchs.w4at;

class Association {

    Association(String aCharacter, String anAssociate) {

        if (isNullOrWhiteSpace(aCharacter)) {
            throw new IllegalArgumentException("aCharacter");
        }

        if (isNullOrWhiteSpace(anAssociate)) {
            throw new IllegalArgumentException("anAssociate");
        }

        character = aCharacter;
        associate = anAssociate;
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
               associate.equals(other.associate);
    }

    @Override
    public int hashCode() {
        return (3 * character.hashCode()) + (5 * associate.hashCode());
    }

    @Override
    public String toString() {
        return character + " met " + associate;
    }

    String getCharacter () {
        return character;
    }

    String getAssociate() {
        return associate;
    }

    private boolean isNullOrWhiteSpace(String aCharacter) {
        return aCharacter == null || aCharacter.trim().isEmpty();
    }

    private final String character;
    private final String associate;
}
