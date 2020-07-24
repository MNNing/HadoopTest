package cn.gary.jdbc;

import java.sql.*;

public class HiveUtil {
    static {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static String url = "jdbc:hive2://192.168.111.201:10000/default";
    static String username = "root";
    static String userpwd = "mnnnb";
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet rs = null;

    public static ResultSet query(String sql) {
        // 编写JDBC代码
        try {
            connection = DriverManager.getConnection(url, username, userpwd);
            //sql 注入问题
            preparedStatement = connection.prepareStatement(sql);
            //提交数据库 执行sql
            rs = preparedStatement.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;

    }

    public static void close(){
        try {
            if(rs!=null || !rs.isClosed()){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String sql = "select brand_id, sum(order_price) as order_price from t_orders group by brand_id";
        ResultSet rs2 = HiveUtil.query(sql);
        try {
            while(rs2.next()){
                System.out.println(String.format("%s , %s", rs2.getString(1), rs2.getString(2)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
