package org.example;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class KMeansReducer extends Reducer<Text, DoubleWritable[],Text,DoubleWritable[]> {
    @Override
    protected void reduce(Text key, Iterable<DoubleWritable[]> values, Reducer<Text, DoubleWritable[], Text,
            DoubleWritable[]>.Context context) throws IOException, InterruptedException {

        Iterator<DoubleWritable[]> iterator = values.iterator();
        double x=0;
        double y=0;
        int counter=0;
        while (iterator.hasNext()){
            List<DoubleWritable> points = new ArrayList<>();
            for (DoubleWritable point : iterator.next()) {
                points.add(point);
            }
            x+=(points.get(0)).get();
            y+=(points.get(1)).get();
            counter++;
        }
        x/=counter;
        y/=counter;
        context.write(key,new DoubleWritable[]{new DoubleWritable(x),new DoubleWritable(y)});

    }
}
