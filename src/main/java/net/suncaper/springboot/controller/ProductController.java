package net.suncaper.springboot.controller;

import net.suncaper.springboot.domain.Product;
import net.suncaper.springboot.domain.User;
import net.suncaper.springboot.service.ProductService;
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
import java.io.IOException;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/showproduct")    //跳转至productList页面展示查询产品表product中的所有元组
    public String goProductListPage(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Model model) {
        model.addAttribute("products", productService.listProduct());
        return "productlist";
    }

    @GetMapping("/addproduct")
    public String goProductAddPage(Model model) {
        model.addAttribute("product", new Product());
        return "product-add";
    }

    @GetMapping("/checkout")      //订单页面的实验
    public String goCheckOutPage(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model) {
        model.addAttribute("products", productService.listProduct());
        return "checkout";
    }

    @GetMapping("/shoppingcart")  //跳转到购物车页面shoppingcart
    public String goShoppingcartPage(HttpServletRequest request,Model model) {
        model.addAttribute("user", new User());
        Boolean isLogin = request.getSession().getAttribute("USER_ID") != null;
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("products", productService.listProduct());
        return "/shoppingcart";
    }


    @PostMapping("/addproduct")
    public String saveProduct(Product product,@RequestParam("file") MultipartFile file) throws IOException {
        if(file!=null){
            product.setFilecontent(file.getBytes());
            product.setFiletitle(file.getOriginalFilename());
            product.setFilelenth(file.getSize());
            product.setFiletype(file.getContentType());
        }
        productService.saveProduct(product);
        return "redirect:/product/showproduct";
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("id") String id){
        Product product=productService.findProductByPrimaryKey(id);
        return ResponseEntity.ok()
                .contentLength(product.getFilelenth())
                .contentType(MediaType.parseMediaType(product.getFiletype()))
                .body(new ByteArrayResource(product.getFilecontent()));
    }


    @GetMapping("/delete")   //delete页面，暂未定义

    @ResponseBody
    public Boolean deleteProduct(@RequestParam("id") String id) {  //在search页面删除一个用户
        productService.deleteProductById(id);
        return true;
    }

}
