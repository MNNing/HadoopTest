package cn.gary.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * 聚合计算Reducer
 */

public class MapReduceWordsCountReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    //北京<1,1,1,1,1,1,1,1,1>
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        //这里的代码你说了算，其他的地方都是有约定的
        int count = 0;
        Iterator<IntWritable> iterator = values.iterator();

        while(iterator.hasNext()){
            count += iterator.next().get();
        }
        context.write(key,new IntWritable(count));


    }
}
