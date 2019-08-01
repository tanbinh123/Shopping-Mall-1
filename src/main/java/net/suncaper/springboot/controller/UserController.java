package net.suncaper.springboot.controller;

import net.suncaper.springboot.domain.User;
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



//    @PostMapping("/add")     //在注册界面提交表单后将数据写入数据库，然后跳转到登录页面login
//    public String saveUser(User user) {
//        userService.saveUser(user);
//        return "login";
//    }

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
        }
        return  "redirect:/user/index";
    }
    @GetMapping("/index")    //主页
    public String goIndexPage(HttpServletRequest request, Model model) {
        Boolean isLogin = request.getSession().getAttribute("USER_ID") != null;
        model.addAttribute("isLogin", isLogin);
        return "index";
    }

    @GetMapping("/shoppingcart")  //跳转到购物车页面shoppingcart
    public String goShoppingcartPage(HttpServletRequest request,Model model) {
        model.addAttribute("user", new User());
        Boolean isLogin = request.getSession().getAttribute("USER_ID") != null;
        model.addAttribute("isLogin", isLogin);
        return "/shoppingcart";
    }

    @GetMapping("/product_0001")  //跳转至1号商品页面
    public String a(Model model) {
        model.addAttribute("user", new User());
        return "/product_0001";
    }

    @GetMapping("/product_0002")  //跳转至2号商品页面
    public String a2(Model model) {
        model.addAttribute("user", new User());
        return "/product_0002";
    }



    @GetMapping("/delete")   //delete页面，暂未定义
    @ResponseBody
    public Boolean deleteUser(@RequestParam("id") String id) {  //在search页面删除一个用户
        userService.deleteUserById(id);
        return true;
    }

    @GetMapping("/edit")    //目前打开出现错误，与id这个元素不存在有关
    public String goUserEditPage(@RequestParam("id") String id, Model model) {
        User user = userService.findUserByPrimaryKey(id);
        model.addAttribute("user", user);
        return "user-add";
    }

    @GetMapping("/select")   //select页面，暂未定义
    @ResponseBody
    public User selectUser(@RequestParam("id") String id) {  //在search页面查找一个用户
        User selectu=userService.findUserByPrimaryKey(id);
        return selectu;
    }
}