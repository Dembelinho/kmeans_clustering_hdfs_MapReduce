package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class Main {
    public static void main(String[] args)throws IOException, URISyntaxException {
        //System.out.println("Hello world!");
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);
        job.addCacheFile(new URI("/centers"));
    }
}