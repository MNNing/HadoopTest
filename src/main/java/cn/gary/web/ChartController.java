package cn.gary.web;

import cn.gary.jdbc.HiveUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping("/charts")
public class ChartController {

    @RequestMapping("/1")
    public String chart1(){

        return "chart1";

    }

    @RequestMapping("2")
    public String chart2(Model model){
        String sql = "select brand_id, sum(order_price) from t_orders group by brand_id";
        StringBuffer sbBrandIds = new StringBuffer();
        StringBuffer sbMoneys = new StringBuffer();
        try {
            ResultSet rs = HiveUtil.query(sql);
            while(rs.next()){
                //System.out.println(String.format("%s , %s", rs.getString(1), rs.getString(2)));
                sbBrandIds.append(rs.getString(1) + ",");
                sbMoneys.append(rs.getString(2) + ",");
            }
            String strBrandIds = sbBrandIds.substring(0, sbBrandIds.length()-1);
            String strMoneys = sbMoneys.substring(0, sbMoneys.length()-1);
            model.addAttribute("sbBrandIds", "["+strBrandIds+"]");
            model.addAttribute("sbMoneys", "["+strMoneys+"]");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HiveUtil.close();


        return "chart2";
    }

    @RequestMapping("3")
    public String chart3(Model model){
        String sql = "select * from t_order_prebrandsum limit 0, 5";
        StringBuffer sbBrandIds = new StringBuffer();
        StringBuffer sbMoneys = new StringBuffer();
        try {
            ResultSet rs = HiveUtil.query(sql);
            while(rs.next()){
                //System.out.println(String.format("%s , %s", rs.getString(1), rs.getString(2)));
                sbBrandIds.append(String.format("'%s',", rs.getString(1)));
                sbMoneys.append(rs.getString(2) + ",");
            }
            String strBrandIds = sbBrandIds.substring(0, sbBrandIds.length()-1);
            String strMoneys = sbMoneys.substring(0, sbMoneys.length()-1);
            model.addAttribute("sbBrandIds", "["+strBrandIds+"]");
            model.addAttribute("sbMoneys", "["+strMoneys+"]");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HiveUtil.close();

        return "chart3";
    }

    //显示视图界面
    @RequestMapping("/4")
    public String chart4(){
        return "chart4";
    }

    //向视图提供数据支持ajax
    @RequestMapping("/4data")
    @ResponseBody
    public String chart4Data(int length){
        String result = "";
        String sql = "select * from t_order_prebrandsum limit 0, "+length;
        StringBuffer sbBrandIds = new StringBuffer();
        StringBuffer sbMoneys = new StringBuffer();
        try {
            ResultSet rs = HiveUtil.query(sql);
            while(rs.next()){
                //System.out.println(String.format("%s , %s", rs.getString(1), rs.getString(2)));
                sbBrandIds.append(String.format("'%s',", rs.getString(1)));
                sbMoneys.append(rs.getString(2) + ",");
            }
            String strBrandIds = sbBrandIds.substring(0, sbBrandIds.length()-1);
            String strMoneys = sbMoneys.substring(0, sbMoneys.length()-1);
            result = String.format("[%s]-[%s]", strBrandIds , strMoneys);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HiveUtil.close();

        return result;
    }
}
