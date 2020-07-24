package cn.gary.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.mortbay.jetty.HttpConnection;

import java.io.IOException;

/**
 * 并行计算Mapper
 */

public class MapReduceWordsCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //这里的代码你说了算，其他的地方都是有约定的
        String line = value.toString();
        String[] words = line.split(" ");
        for(String word : words){
            Text wordkey = new Text(word);
            IntWritable wordVal = new IntWritable(1);
            context.write(wordkey,wordVal);
        }

    }
}
