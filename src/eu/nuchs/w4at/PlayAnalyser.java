package eu.nuchs.w4at;

import java.lang.*;
import java.util.*;

class PlayAnalyser {
    public PlayAnalyser(LineAnalyser lineAnalyser) {
        if (lineAnalyser == null) {
            throw new NullPointerException("analyser");
        }

        analyser = lineAnalyser;
    }

    List<Association> GetNewAssociations() {
        ArrayList<Association> associations = new ArrayList<Association>();

        if (thereIsANewSpeaker()) {
            for (String character : characters) {
                associateExistingCharacterWithNewSpeaker(character, associations);
            }
        }

        return associations;
    }

    void addLine(String line) {
        LineType type = analyser.analyse(line);
        newSpeaker = "";

        if (someoneSpoke(type)) {
            String speaker = analyser.getSpeaker(line);
            recordIfNew(speaker);
            newScene = false;
        } else if (aNewSceneHasStarted(type)) {
            characters.clear();
            scene = "nowhere";
            newScene = true;
        } else if (newScene) {
            scene = line.trim();
            newScene = false;
        }
    }

    private void associateExistingCharacterWithNewSpeaker(String character, ArrayList<Association> associations) {
        if (!newSpeaker.equals(character)) {
            associations.add(new Association(character, newSpeaker, scene));
            associations.add(new Association(newSpeaker, character, scene));
        }
    }

    private void recordIfNew(String speaker) {
        if (!characters.contains(speaker)) {
            newSpeaker = speaker;
            characters.add(newSpeaker);
        }
    }

    private boolean thereIsANewSpeaker() {
        return !newSpeaker.equals("");
    }

    private boolean someoneSpoke(LineType type) {
        return type == LineType.CHARACTER;
    }

    private boolean aNewSceneHasStarted(LineType type) {
        return type == LineType.SCENE;
    }

    private boolean newScene = false;
    private String newSpeaker = "";
    private String scene="nowhere";
    private LineAnalyser analyser;
    private ArrayList<String> characters = new ArrayList<String>();
}
