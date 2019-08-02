package net.suncaper.springboot.controller;

import net.suncaper.springboot.domain.Admin;
import net.suncaper.springboot.domain.Product;
import net.suncaper.springboot.service.AdminService;
import net.suncaper.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ProductService productService;
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
        @GetMapping("/goodslist")   //跳转到商品列表
        public String goGoodsTablesPage(Model model) {
        model.addAttribute("addProduct",new Product());
        model.addAttribute("productsList",adminService.getProductsList());

            return "layouts-goodstables";
    }

    @GetMapping("/img/{id}")//从图片库里拿图片
    public ResponseEntity<Resource> getImage(@PathVariable("id") String id) throws IOException {
        Product product = productService.findProductByPrimaryKey(id);
        String filePath="C:\\databaseimg\\";
        byte[] body=null;
        InputStream fileInputStream = new FileInputStream(filePath + product.getFiletitle());
        body=new byte[product.getFilelenth().intValue()];
        fileInputStream.read(body);
        return ResponseEntity.ok()
                .contentLength(product.getFilelenth())
                .contentType(MediaType.parseMediaType(product.getFiletype()))
                .body(new ByteArrayResource(body));
    }

    @PostMapping("/addproduct")//新增商品
    public String addProductInfo(Product product,MultipartFile file) throws IOException {
        if (file.getSize()!=0){
            //保存图片的路径
            String filePath="C:\\databaseimg";
            //获取原始图片的拓展名
            String originalFilename = file.getOriginalFilename();
            //新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复
            String newFileName = UUID.randomUUID()+originalFilename;
            //封装上传文件位置的全路径，就是硬盘路径+文件名
            File targetFile = new File(filePath,newFileName);
            //把本地文件上传到已经封装好的文件位置的全路径就是上面的targetFile
            file.transferTo(targetFile);
            //文件名保存到实体类对应属性上
            product.setFiletitle(newFileName);
            product.setFilelenth(file.getSize());
            product.setFiletype(file.getContentType());
        }
        productService.saveProduct(product);
        return "redirect:/admin/goodslist";
    }

    @PostMapping("/deleteproduct")//删除商品
    public String deleteProductInfo(Product product){
        productService.deleteProductById(product.getId());
        return "redirect:/admin/goodslist";
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
