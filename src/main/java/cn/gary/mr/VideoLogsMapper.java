package cn.gary.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 并行计算Mapper
 */

public class VideoLogsMapper extends Mapper<LongWritable, Text,Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //这里的代码你说了算，其他的地方都是有约定的
        String line = value.toString();
        String[] row = line.split("\t");
        if (row.length == 8){
            String name = row[2];
            String time = row[7];

            context.write(new Text(name),new Text(time));

        }

    }
}
