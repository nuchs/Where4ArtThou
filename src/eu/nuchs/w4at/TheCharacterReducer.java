package eu.nuchs.w4at;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TheCharacterReducer extends Reducer<Text, HadoopAssociation, Text, Text> {

    @Override
    protected void reduce(Text characterName, Iterable<HadoopAssociation> associates, Context context) throws IOException, InterruptedException {

        Character character = new Character(characterName.toString());

        for (HadoopAssociation associateEvent : associates) {
            Text associate = associateEvent.getAssociate();
            Text location = associateEvent.getLocation();
            character.addAssociate(associate.toString(), location.toString());
        }

        context.write(dummyKey, new Text(character.toJson()));
    }

    private final Text dummyKey = new Text("");
}
