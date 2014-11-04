package eu.nuchs.w4at;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TheReducer extends Reducer<Text,Text,Text,Text> {

    @Override
    protected void reduce(Text characterName, Iterable<Text> associates, Context context) throws IOException, InterruptedException {

        Character character = new Character(characterName.toString());

        for ( Text associate : associates ) {
            character.addAssociate(associate.toString());
        }

        context.write(dummyKey, new Text(character.toJson()));
    }

    private final Text dummyKey = new Text("");
}
