package net.suncaper.springboot.controller;

import net.suncaper.springboot.domain.Admin;
import net.suncaper.springboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/login")
    public String goAdminLoginPage(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("admin")==null){
            model.addAttribute("admin" ,new Admin());
        }
        return "htlogin";
    }

    @PostMapping("/login")   //在登录页面提交表单后判断管理员用户名、密码是否正确
    public String goIndexPage(HttpServletRequest request, Admin admin) {
        List<Admin> admins = adminService.login(admin);
        if (admins.isEmpty()) {
            return  "redirect:/admin/login";
        }
        else{
           request.getSession().setAttribute("ADMIN_ID", admins.get(0).getId());
            return  "redirect:/admin/index";

        }
    }
    @GetMapping("/index")    //主页
    public String goIndexPage(HttpServletRequest request, Model model) {
            model.addAttribute("admin", new Admin());
            Boolean isLogin = request.getSession().getAttribute("ADMIN_ID") != null;
            model.addAttribute("isLogin", isLogin);
            return "htindex";
    }
    @GetMapping("/adminlist")   //跳转到
    public String goAdminTablePage(Model model) {

        return "layouts-admintables";

    }
        @GetMapping("/goodslist")   //跳转到
        public String goGoodsTablesPage(Model model) {

            return "layouts-goodstables";
    }

    @GetMapping("/incomelist")   //跳转到
    public String goIncomeTablesPage(Model model) {

        return "layouts-incometables";
    }
     @GetMapping("/orderlist")   //跳转到
    public String goOrderTablesPage(Model model) {

        return "layouts-ordertables";
    }

    @GetMapping("/userlist")   //跳转到
    public String goUsersTablesPage(Model model) {

        return "layouts-userstables";
    }


    @GetMapping("/coustomlimit")   //跳转到
    public String goCoustomLimitPage(Model model) {

        return "coustomlimit";
    }

    @GetMapping("/adminlimit")   //跳转到
    public String goAdminLimitPage(Model model) {

        return "adminlimit";
    }

    @GetMapping("/userchartjs")   //跳转到
    public String goUserChartJsPage(Model model) {

        return "chartjs";
    }

    @GetMapping("/incomechartjs")   //跳转到
    public String goIncomeChartJsPage(Model model) {

        return "chartjs2";
    }

    @GetMapping("/admininform")   //跳转到
    public String goAdminInformPage(Model model) {

        return "forms";
    }
    @GetMapping("/nofound")   //跳转到
    public String goNoFoundPage(Model model) {

        return "404";
    }
    @GetMapping("/bug")   //跳转到
    public String goBugPage(Model model) {

        return "505";
    }


}
