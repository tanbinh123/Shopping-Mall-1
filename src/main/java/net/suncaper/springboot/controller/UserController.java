package net.suncaper.springboot.controller;

import net.suncaper.springboot.domain.Product;
import net.suncaper.springboot.domain.Shoppingcart;
import net.suncaper.springboot.domain.User;
import net.suncaper.springboot.service.AdminService;
import net.suncaper.springboot.service.ShoppingcartService;
import net.suncaper.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ShoppingcartService shoppingcartService;

    @GetMapping("/search")    //跳转至userList页面展示查询用户表t_user中的所有元组
    public String goUserListPage(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model) {
        model.addAttribute("users", userService.listUser());
        return "userList";
    }

    @GetMapping("/add")       //跳转到注册界面user-add
    public String goUserAddPage(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/add")     //在注册界面提交表单后将数据写入数据库，成功则跳转到登录页面login，失败则注册界面
    public String saveUser(User user) {
        if(userService.saveUser(user)==true){
            return "login";
        }
        else {
            return "user-add";
        }
    }

    @GetMapping("/exitlogin")//用户退出登录
    public String exitLogin(HttpServletRequest request){
        request.getSession().setAttribute("USER_ID",null);
        return "redirect:/user/index";
    }

    @GetMapping("/login")   //跳转到登录页面login
    public String goLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")   //在登录页面提交表单后判断用户名、密码是否正确
    public String goIndexPage(HttpServletRequest request,User user) {
        User loginUser=userService.login(user);
        if (loginUser!=null) {
            request.getSession().setAttribute("USER_ID", loginUser.getId());
            request.getSession().setAttribute("USER_name", loginUser.getName());
            request.getSession().setAttribute("CART_num",shoppingcartService.selectByUserID(loginUser.getId()).size());
        }
        return  "redirect:/user/index";
    }
    @GetMapping("/index")    //主页
    public String goIndexPage() {
        return "index";
    }

    @RequestMapping("/searchproduct") //搜索结果界面
    public String goSearchProductResultPage(String keyword, Model model){
        model.addAttribute("productsList",adminService.getProductsListByName(keyword));
        model.addAttribute("keyword",keyword);
        return "searchproduct";
    }


    @GetMapping("/delete")   //delete
    @ResponseBody
    public Boolean deleteUser(@RequestParam("id") String id) {  //在search页面删除一个用户
        userService.deleteUserById(id);
        return true;
    }

    @GetMapping("/edit")    //edit
    public String goUserEditPage(@RequestParam("id") String id, Model model) {
        User user = userService.findUserByPrimaryKey(id);
        model.addAttribute("user", user);
        return "user-add";
    }

    @GetMapping("/select")   //select
    @ResponseBody
    public User selectUser(@RequestParam("id") String id) {  //在search页面查找一个用户
        User selectu=userService.findUserByPrimaryKey(id);
        return selectu;
    }

}