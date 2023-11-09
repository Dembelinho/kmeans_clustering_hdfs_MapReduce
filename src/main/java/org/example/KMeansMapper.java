package org.example;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.*;
import java.net.URI;
import java.util.*;

public class KMeansMapper extends Mapper<LongWritable, Text,Text, DoubleWritable[]> {
    BufferedReader bufferedReader;
   // private final static LongWritable CENTROID_KEY = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable[]>.Context context)
            throws IOException, InterruptedException {
       //lire les valeurs des centroides
        List<Double[]> centers = readCenters();

        Map<Integer, String> dict = new HashMap<>();
        dict.put(0, "c1");
        dict.put(1, "c2");
        dict.put(2, "c3");

        //extraire les valeurs
        String[] s = value.toString().split(",");
        double x = Double.parseDouble(s[0]);
        double y = Double.parseDouble(s[1]);

        //calcule de la distance euclidienne
        ArrayList<Double> dist=new ArrayList<>();
        centers.forEach(c->{
            double d = Math.sqrt(Math.pow(x - c[0], 2) + Math.pow(y - c[1], 2));
            dist.add(d);
        });

        Double max = Collections.max(dist);
        String c = dict.get(max);
        context.write(new Text(c),new DoubleWritable[]{new DoubleWritable(x),new DoubleWritable(y)});

        // Convertit la ligne du fichier de données en un objet Point   Point point = new Point(value.toString());

        // Calcule la distance entre le point et chacun des centroïdes initiaux   Map<Integer, Double> distances = point.distances(getCentroids());

        // Trouve le centroïde le plus proche //int nearestCentroid = distances.keySet().iterator().next();

        // Émet le centroïde le plus proche et le point //CENTROID_KEY.set(nearestCentroid);
        //context.write(CENTROID_KEY, point);
    }

    private List<Double[]> readCenters() throws IOException {
        String line;
        List<Double[]> centers=new ArrayList<Double[]>();
        while ((line=bufferedReader.readLine())!=null){
            String[] s = line.split(","); // le séparateur est une ,
            centers.add(new Double[]{Double.parseDouble(s[0]),Double.parseDouble(s[1])});
        }
        return centers;
    }
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, DoubleWritable[]>.Context context)
            throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        if(cacheFiles!=null && cacheFiles.length>0){
            try {
                FileSystem fileSystem = FileSystem.get(context.getConfiguration());
                Path path = new Path(cacheFiles[0].toString());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileSystem.open(path)));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}

