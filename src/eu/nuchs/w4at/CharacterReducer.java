package eu.nuchs.w4at;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CharacterReducer extends Reducer<Text,IntWritable,Text,IntWritable> {

    private final IntWritable wordCount = new IntWritable(0);

    @Override
    protected void reduce(Text word, Iterable<IntWritable> instances, Context context) throws IOException, InterruptedException {

        int count = 0;

        for (IntWritable dummy : instances) {
            count++;
        }

        wordCount.set(count);

        context.write(word, wordCount);
    }
}
