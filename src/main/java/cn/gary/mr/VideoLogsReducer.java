package cn.gary.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * 聚合计算Reducer
 */

public class VideoLogsReducer extends Reducer<Text, Text,Text,Text> {

    //
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        //这里的代码你说了算，其他的地方都是有约定的
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = null;
        try {
            now = dateFormat.parse("2020-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);


        Iterator iterator = values.iterator();
        while(iterator.hasNext()){
            String time = iterator.next().toString();
            //时间累计
            try {
                String[] times = time.split(":");
                int hours = Integer.valueOf(times[0]);
                int minutes = Integer.valueOf(times[1]);
                int seconds = Integer.valueOf(times[2]);
                calendar.add(Calendar.HOUR, hours);
                calendar.add(Calendar.MINUTE, minutes);
                calendar.add(Calendar.SECOND, seconds);
            }catch (Exception e){
            }
            //时间累计

        }

        Date lastDate = calendar.getTime();

        context.write(key, new Text(dateFormat.format(lastDate)));



    }
}
