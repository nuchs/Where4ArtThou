package eu.nuchs.w4at;

import java.util.regex.*;

class LineAnalyser {

    LineType analyse(String line) {
        if (startsAScene(line)) {
            return LineType.SCENE;
        } else if (characterStartsSpeaking(line)) {
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

    private boolean characterStartsSpeaking(String line) {
        Matcher match = newSpeaker.matcher(line);
        return match.find();
    }

    private boolean startsAScene(String line) {
        Matcher match = newScene.matcher(line);
        return match.find();
    }

    private Pattern newScene = Pattern.compile("^(SCENE|ACT)\\b");
    private Pattern newSpeaker = Pattern.compile("^\\s+([A-Z][A-Za-z]+)\\.");
}
