package eu.nuchs.w4at;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class HadoopAssociation implements Writable {

    public void set(String anAssociate, String aLocation) {

        if (isNullOrWhiteSpace(anAssociate)) {
            throw new IllegalArgumentException("anAssociate");
        }

        if (isNullOrWhiteSpace(aLocation)) {
            throw new IllegalArgumentException("aLocation");
        }

        associate = new Text(anAssociate);
        location = new Text(aLocation);
    }

    public Text getLocation() {
        return location;
    }

    public Text getAssociate() {
        return associate;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        associate.write(out);
        location.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        associate.readFields(in);
        location.readFields(in);
    }

    private boolean isNullOrWhiteSpace(String word) {
        return word == null || word.trim().isEmpty();
    }

    private Text associate;
    private Text location;
}
