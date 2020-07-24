package cn.gary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 定义一个WebController
 */
@Controller
public class IndexController {
    /**
     * 第一个由Java书写的网页
     */
    @RequestMapping("/index")
    public String index(){

        return "index";
    }

    @GetMapping("/login")
    public String login(){
        //加载视图
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(String username, String password, HttpSession session,Model model){
        //获取请求数据 username password

        //验证是否有权利使用系统
        if("admin".equals(username)&& "admin888".equals(password)){
            session.setAttribute("username","admin");
            //页面跳转
            return "redirect:index";
        }
        //用户名密码错误
        String info = "管理员账号密码、错误，请重新输入";
        model.addAttribute("info",info);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清理会话数据
        session.invalidate();
        //页面跳转
        return "redirect:login";

    }



}
