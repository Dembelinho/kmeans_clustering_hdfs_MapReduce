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

    private final static LongWritable CENTROID_KEY = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable[]>.Context context)
            throws IOException, InterruptedException {

        BufferedReader bufferedReader;

        // Convertit la ligne du fichier de données en un objet Point   Point point = new Point(value.toString());

        // Calcule la distance entre le point et chacun des centroïdes initiaux   Map<Integer, Double> distances = point.distances(getCentroids());

        // Trouve le centroïde le plus proche
        int nearestCentroid = distances.keySet().iterator().next();

        // Émet le centroïde le plus proche et le point
        CENTROID_KEY.set(nearestCentroid);
        context.write(CENTROID_KEY, point);
    }

    /**
     * Obtient les centroïdes initiaux à partir du cache distribué.
     *
     * @return Les centroïdes initiaux.
     * @throws IOException
     * @throws InterruptedException
     */
    private Map<Integer, Point> getCentroids() throws IOException, InterruptedException {
        // Récupère le cache distribué
        Map<String, Map<Integer, Point>> cache = context.getCache();

        // Récupère le cache des centroïdes
        Map<Integer, Point> centroids = cache.get("centroids");

        return centroids;
    }
}

