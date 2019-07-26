package net.suncaper.springboot.controller;

import net.suncaper.springboot.domain.User;
import net.suncaper.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public String goUserListPage(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model) {
        model.addAttribute("users", userService.listUser());
        return "userList";
    }

    @GetMapping("/add")
    public String goUserAddPage(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/add")
    public String saveUser(User user) {
        userService.saveUser(user);

        return "login";
    }

    @GetMapping("/edit")
    public String goUserEditPage(@RequestParam("id") String id, Model model) {
        User user = userService.findUserByPrimaryKey(id);
        model.addAttribute("user", user);
        return "user-add";
    }


    @GetMapping("/index")
    public String goIndexPage(HttpServletRequest request, Model model) {
        model.addAttribute("user", new User());

        Boolean isLogin = request.getSession().getAttribute("USER_ID") != null;
        model.addAttribute("isLogin", isLogin);
        return "index";
    }

    @GetMapping("/login")
    public String goLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @GetMapping("/shoppingcart")
    public String goShoppingcartPage(HttpServletRequest request,Model model) {
        model.addAttribute("user", new User());
        Boolean isLogin = request.getSession().getAttribute("USER_ID") != null;
        model.addAttribute("isLogin", isLogin);
        return "/shoppingcart";
    }

    @PostMapping("/login")

    public String goIndexPage(HttpServletRequest request, String userName, String password, User user, Model model) {
        User loginUser=userService.login(user);
        if (loginUser!=null) {
            request.getSession().setAttribute("USER_ID", "loginUser.getId()");

        }
        return  "redirect:/user/index";
    }

    @GetMapping("/delete")
    @ResponseBody
    public Boolean deleteUser(@RequestParam("id") String id) {
        userService.deleteUserById(id);
        return true;
    }
}