package eu.nuchs.w4at;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

import java.io.IOException;

public class CharacterMapper extends Mapper<LongWritable,Text,Text,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        scene.addLine(value.toString());

        for (Association association : scene.GetNewAssociations()) {
            character.set(association.getCharacter());
            associate.set(association.getAssociate());

            context.write(character, associate);
        }
    }

    private Text character = new Text();
    private Text associate = new Text();
    private SceneAnalyser scene = new SceneAnalyser(new LineAnalyser());
}
