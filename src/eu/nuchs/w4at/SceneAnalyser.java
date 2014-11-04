package eu.nuchs.w4at;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

public class SceneAnalyser {
    public SceneAnalyser(LineAnalyser lineAnalyser) {
        if (lineAnalyser == null) {
            throw new NullPointerException("analyser");
        }

        analyser = lineAnalyser;
    }

    public List<Association> GetNewAssociations() {
        ArrayList<Association> associations = new ArrayList<Association>();

        if(thereIsANewSpeaker()) {
            for (String character : characters) {
                associateExistingCharacterWithNewSpeaker(character, associations);
            }
        }

        return associations;
    }

    public void addLine(String line) {
        LineType type = analyser.analyse(line);
        newSpeaker = "";

        if (someoneSpoke(type)) {
            String speaker = analyser.getSpeaker(line);
            recordIfNew(speaker);
        } else if (aNewSceneHasStarted(type)) {
            characters.clear();
        }
    }

    private void associateExistingCharacterWithNewSpeaker(String character, ArrayList<Association> associations) {
        if (!newSpeaker.equals(character)) {
            associations.add(new Association(character, newSpeaker));
            associations.add(new Association(newSpeaker, character));
        }
    }

    private void recordIfNew(String speaker) {
        if(!characters.contains(speaker)) {
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

    String newSpeaker = "";
    private LineAnalyser analyser;
    private ArrayList<String> characters = new ArrayList<String>();
}
