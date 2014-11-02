package eu.nuchs.w4at;

public class Association {

    Association(String aCharacter, String anAssociate) {
        character = aCharacter;
        associate = anAssociate;
    }

    String getCharacter () {
        return character;
    }

    String getAssociate() {
        return associate;
    }

    private final String character;
    private final String associate;
}
