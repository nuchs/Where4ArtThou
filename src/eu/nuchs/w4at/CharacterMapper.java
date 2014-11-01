package eu.nuchs.w4at;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

import java.io.IOException;

public class CharacterMapper extends Mapper<Text,Text,Text,Text> {

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {

    }
}
