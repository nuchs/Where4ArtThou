package eu.nuchs.w4at;

import java.util.regex.*;

class LineAnalyser {

    LineType analyse(String line) {
        if (startsAScene(line)) {
            return LineType.SCENE;
        } else if (characterStartsSpeaking(line)) {
            return LineType.SPEAKER;
        } else if (startsTheCharacterList(line)) {
            return LineType.DRAMATIS_PERSONAE;
        } else if (describesANewCharacter(line)) {
            return LineType.CHARACTER;
        } else {
            return LineType.UNCLASSIFIED;
        }
    }

    String getSpeaker(String line) {
        Matcher match = newSpeaker.matcher(line);

        if (match.find()) {
            return match.group(1);
        }

        throw new IllegalArgumentException("No one started speaking on line : " + line);
    }

    public String getCharacter(String line) {
        Matcher match = newCharacter.matcher(line);

        if (match.find()) {
            return match.group(1);
        }

        throw new IllegalArgumentException("No character description on line : " + line);
    }

    private boolean characterStartsSpeaking(String line) {
        Matcher match = newSpeaker.matcher(line);
        return match.find();
    }

    private boolean startsAScene(String line) {
        Matcher match = newScene.matcher(line);
        return match.find();
    }

    private boolean startsTheCharacterList(String line) {
        Matcher match = characterList.matcher(line);
        return match.find();
    }

    private boolean describesANewCharacter(String line) {
        Matcher match = newCharacter.matcher(line);
        return match.find();
    }

    private Pattern characterList = Pattern.compile("^\\s*DRAMATIS PERSONAE\\b");
    private Pattern newScene = Pattern.compile("^(SCENE|ACT)\\b");
    private Pattern newSpeaker = Pattern.compile("^\\s+([A-Z][A-Za-z]+)\\.");
    private Pattern newCharacter = Pattern.compile("^\\s+([A-Z][A-Za-z]+),");
}
