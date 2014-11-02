package eu.nuchs.w4at;

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

        for (String character : characters) {
            for(String associate : characters) {
                associations.add(new Association(character, associate));
            }
        }

        return associations;
    }

    public void addLine(String line) {
        LineType type = analyser.analyse(line);

        if (type == LineType.CHARACTER) {
            characters.add(analyser.getSpeaker(line));
        } else if (type == LineType.SCENE) {
            characters.clear();
        }
    }

    private LineAnalyser analyser;
    private ArrayList<String> characters = new ArrayList<String>();
}
