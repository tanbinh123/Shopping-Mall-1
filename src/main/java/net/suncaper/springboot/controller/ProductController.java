package net.suncaper.springboot.controller;

import net.suncaper.springboot.domain.Product;
import net.suncaper.springboot.domain.Shoppingcart;
import net.suncaper.springboot.domain.User;
import net.suncaper.springboot.service.ProductService;
import net.suncaper.springboot.service.ShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingcartService shoppingcartService;
//    @GetMapping("/showproduct")    //跳转至productList页面展示查询产品表product中的所有元组
//    public String goProductListPage(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    Model model) {
//        model.addAttribute("products", productService.listProduct());
//        return "productlist";
//    }

        @GetMapping("/showshoppingcartlist")
    public String goShoppingcartListPage(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Model model) {
        model.addAttribute("shoppingcarts", shoppingcartService.listShoppingcart());
        return "shoppingcartlist";
    }






//    @GetMapping("/addproduct")
//    public String goProductAddPage(Model model) {
//        model.addAttribute("product", new Product());
//        return "product-add";
//    }

    @GetMapping("/checkout")      //订单页面的实验
    public String goCheckOutPage(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model) {
        model.addAttribute("products", productService.listProduct());
        return "checkout";
    }

    @GetMapping("/img/{id}")//从图片库里拿图片
    public ResponseEntity<Resource> getShoppingcartImage(@PathVariable("id") String id) throws IOException {
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

    @GetMapping("/shoppingcart")  //跳转到购物车页面shoppingcart
    public String goShoppingcartPage(HttpServletRequest request,Model model) {
        model.addAttribute("user", new User());
        Boolean isLogin = request.getSession().getAttribute("USER_ID") != null;
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("products", productService.listProduct());
        return "/shoppingcart";
    }


//    @PostMapping("/addproduct")
//    public String saveProduct(Product product,@RequestParam("file") MultipartFile file) throws IOException {
//        if(file!=null){
//            product.setFiletitle(file.getOriginalFilename());
//            product.setFilelenth(file.getSize());
//            product.setFiletype(file.getContentType());
//        }
//        productService.saveProduct(product);
//        return "redirect:/product/showproduct";
//    }

    @GetMapping("/product_0001")  //跳转至1号商品页面
    public String goProductonePage(Model model){

        model.addAttribute("shoppingcart", new Shoppingcart());


            return "/product_0001";
    }

    @PostMapping("/product_0001")     //在注册界面提交表单后将数据写入数据库，成功则跳转到登录页面login，失败则注册界面
    public String saveProduct1(Shoppingcart shoppingcart) {
        shoppingcartService.saveShoppingcart(shoppingcart);
        return "redirect:/product/showshoppingcartlist";
    }





    @GetMapping("/product_0002")  //跳转至2号商品页面
    public String goProduct_0002Page() {
        return "/product_0002";
    }


    @GetMapping("/delete")

    @ResponseBody
    public Boolean deleteProduct(@RequestParam("id") String id) {
        productService.deleteProductById(id);
        return true;
    }

}
