package cn.gary.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Driver 作业入口
 */

public class MapReduceWordsCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        URI uri = new URI("hdfs://192.168.111.201/");
        Configuration conf = new Configuration();

        FileSystem hdfs = FileSystem.get(uri,conf);

        //进行文件操作,如果hdfs://192.168.111.201/out存在，则删除
        Path dir = new Path("/out");
        if (hdfs.exists(dir)){
            System.out.println("/out文件夹存在，将进行删除\n");
            hdfs.delete(dir,true);
        }
        Job job = new Job(conf, "词频统计作业 Ver1.0");
        //定义启动器
        job.setJarByClass(MapReduceWordsCount.class);
        //定义Mapper处理类
        job.setMapperClass(MapReduceWordsCountMapper.class);
        //定义Reducer处理类
        job.setReducerClass(MapReduceWordsCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path("hdfs://192.168.111.201/data/words.dict"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.111.201/out"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }

}
