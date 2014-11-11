package eu.nuchs.w4at;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

import java.io.IOException;

public class TheMapper extends Mapper<LongWritable, Text, Text, HadoopAssociation> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        play.addLine(value.toString());

        for (Association association : play.GetNewAssociations()) {
            character.set(association.getCharacter());
            associate.set(association.getAssociate(), association.getLocation());

            context.write(character, associate);
        }
    }

    private Text character = new Text();
    private HadoopAssociation associate = new HadoopAssociation();
    private PlayAnalyser play = new PlayAnalyser(new LineAnalyser());
}
