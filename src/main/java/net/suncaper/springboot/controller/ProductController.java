package net.suncaper.springboot.controller;
import net.suncaper.springboot.domain.Product;
import net.suncaper.springboot.domain.Shoppingcart;
import net.suncaper.springboot.domain.User;
import net.suncaper.springboot.service.ProductService;
import net.suncaper.springboot.service.ShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingcartService shoppingcartService;

    @GetMapping("/showshoppingcartlist")
    public String goShoppingcartListPage(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Model model) {
        model.addAttribute("shoppingcarts", shoppingcartService.listShoppingcart());
        return "shoppingcartlist";
    }
    @GetMapping("/shoppingcart")
    public String ShoppingCartPage(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Model model) {
       // model.addAttribute("shoppingcarts", shoppingcartService.listShoppingcart());
        //model.addAttribute("shoppingcarts", new Shoppingcart());
//        model.addAttribute("productName",productService.findProductByPrimaryKey("973642ea-b5c2-11e9-b765-48ba4e461957").getName());
//        model.addAttribute("shoppingcarts",shoppingcartService.findShoppingcartByPrimaryKey(id));
        //        model.addAttribute("user", new User());
//        Boolean isLogin = request.getSession().getAttribute("USER_ID") != null;
//        model.addAttribute("isLogin", isLogin);
        String userID= (String) request.getSession().getAttribute("USER_ID");
        List<Shoppingcart> shoppingcarts=shoppingcartService.selectByUserID(userID);
        List<Product> products =new ArrayList<>();
        for(int i=0;i<shoppingcarts.size();i++){
            products.add(productService.findProductByPrimaryKey(shoppingcarts.get(i).getProId()));
    }
        model.addAttribute("products",products);
        return "shoppingcart";
    }



    @PostMapping("/shoppingcart")
    public String ChangeQuantity(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model, String[] proID,int[] quantity){
        String userID= (String) request.getSession().getAttribute("USER_ID");
        List<Shoppingcart> shoppingcarts=shoppingcartService.selectByUserID(userID);

        for(int i=0;i<shoppingcarts.size();i++){
//            shoppingcartService.findShoppingcartByProID(shoppingcarts.get(i).getProId()).setQuantity(quantity[i]);
//            shoppingcartService.findShoppingcartByProID(proID[i]).setQuantity(quantity[i]);
           Shoppingcart shoppingcart=shoppingcartService.findShoppingcartByProID(proID[i]);
            shoppingcart.setQuantity(quantity[i]);
            shoppingcartService.updateQuantity(shoppingcart);
        }
        return "redirect:/product/checkout";
    }

    @GetMapping("/checkout")      //订单页面的实验
    public String goCheckOutPage(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model) {
        model.addAttribute("products", productService.listProduct());
        return "checkout";
    }




    @GetMapping("/product_0001")  //跳转至1号商品页面
    public String goProductonePage(Model model){

        model.addAttribute("shoppingcart", new Shoppingcart());
            return "/product_0001";
    }

    @PostMapping("/product_0001")
    public String saveProduct1(Shoppingcart shoppingcart) {
        shoppingcartService.saveShoppingcart(shoppingcart);
        return "redirect:/product/shoppingcart";
    }

    @GetMapping("/product_0002")  //跳转至2号商品页面
    public String goProduct_0002Page() {
        return "/product_0002";
    }


    @GetMapping("/deletecart")   //delete
    @ResponseBody
    public Boolean deleteShoppingcart(@RequestParam("id") String id) {
        shoppingcartService.deleteShoppingcartById(id);
        return true;
    }


    @GetMapping("/deletecart2")   //delete
    @ResponseBody
    public Boolean deleteShoppingcart2(@RequestParam("id") String id) {
        shoppingcartService.deleteShoppingcartByProID(id);
        return true;
    }

    @GetMapping("/delete")
    @ResponseBody
    public Boolean deleteProduct(@RequestParam("id") String id) {
        productService.deleteProductById(id);
        return true;
    }


//    @GetMapping("/addproduct")
//    public String goProductAddPage(Model model) {
//        model.addAttribute("product", new Product());
//        return "product-add";
//    }



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

}
