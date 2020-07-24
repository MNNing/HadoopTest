package cn.gary.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFSDemo01 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        //hadoop 客户端的配置对象==》Hadoop中的配置文件（core-site.xml...）
        URI uri = new URI("hdfs://192.168.111.201/");
        Configuration conf = new Configuration();
        conf.set("dfs.blocksize","128m");

        FileSystem hdfs = FileSystem.get(uri,conf);
        //FileSystem.newInstance();

        //进行文件操作
        //1.判断文件或文件夹是否存在
        Path dir = new Path("/mnn");
        if (hdfs.exists(dir)){
            System.out.println("/logs文件夹存在，将进行删除");
            hdfs.delete(dir,true);
        }else{
            System.out.println("/logs文件夹不存在");
        }
        //2.上传数据文件  C:\Users\ASUS\Desktop\shell1.pdf
        //               hdfs://ip/logs/shell1.pdf
        Path logsPath = new Path("/logs");
        if(!hdfs.exists(logsPath)){
            hdfs.mkdirs(logsPath);
        }
        Path src = new Path("C:\\Users\\ASUS\\Desktop\\shell1.pdf");
        Path dst = new Path("/logs/shell1.pdf");
        hdfs.copyFromLocalFile(src,dst);
        System.out.println("上传成功");
        //3.下载数据文件
        Path src1 = new Path("/logs/shell1.pdf");
        Path dst1 = new Path("C:\\Users\\ASUS\\Desktop\\newShell.pdf");
        if(!hdfs.exists(logsPath)){
            System.out.println("文件不存在");
        }else{
            hdfs.copyToLocalFile(src1,dst1);
            System.out.println("下载成功");
        }
        hdfs.close();


    }
}
