package cn.gary.web;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    static List<String> names;

    @RequestMapping("/1")
    public String page1(){
        return "page1";
    }

    static{
        //一般加载自数据源，此处模拟数据
        names = new ArrayList<String>();
        names.add("吴亚尧");
        names.add("温鑫岩");
        names.add("郭雨丰");
        names.add("许荣榕");
        names.add("李昕蔚");
        names.add("王枭阳");
        names.add("董舸");
        names.add("郭利瑛");
        names.add("王燕妮");
        names.add("孙嘉俊");
        names.add("陈玉浩然");
        names.add("包迪");
        names.add("严康建");
        names.add("储志华");
        names.add("胡兆欣");
    }


    @RequestMapping("/getdata")
    @ResponseBody
    public String  getData(Integer index){
        return names.get(index);
    }


    @RequestMapping("/ajaxpage")
    public String ajaxPage(){
        return "ajaxpage";
    }
}
