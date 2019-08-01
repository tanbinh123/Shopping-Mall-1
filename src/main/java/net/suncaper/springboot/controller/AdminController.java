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
    public String goAdminLoginPage() {
        return "htlogin";
    }

    @PostMapping("/login")   //在登录页面提交表单后判断用户名、密码是否正确
    public String goIndexPage(HttpServletRequest request, Admin admin) {
        List<Admin> admins = adminService.login(admin);
        if (admins.isEmpty()) {
            return  "redirect:/admin/login";
        }
        else{
           request.getSession().setAttribute("ADMIN_ID", admins.get(0).getId());
            return  "redirect:/admin/htindex";

        }
    }
    @GetMapping("/index")    //主页
    public String goIndexPage(HttpServletRequest request, Model model) {
        model.addAttribute("admin", new Admin());
        Boolean isLogin = request.getSession().getAttribute("ADMIN_ID") != null;
        model.addAttribute("isLogin", isLogin);
        return "htindex";
    }


}
