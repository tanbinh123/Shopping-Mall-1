package net.suncaper.springboot.controller;
import net.suncaper.springboot.domain.Product;
import net.suncaper.springboot.domain.Shoppingcart;
import net.suncaper.springboot.domain.User;
import net.suncaper.springboot.service.AdminService;
import net.suncaper.springboot.service.ProductService;
import net.suncaper.springboot.service.ShoppingcartService;
import org.apache.ibatis.annotations.Param;
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
    private AdminService adminService;

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
    public String ShoppingCartPage(HttpServletRequest request, Model model) {
        String userID= (String) request.getSession().getAttribute("USER_ID");
        if (userID!=null){
            List<Shoppingcart> shoppingcarts=shoppingcartService.selectByUserID(userID);
            List<Product> products =new ArrayList<>();
            for(int i=0;i<shoppingcarts.size();i++){
                products.add(productService.findProductByPrimaryKey(shoppingcarts.get(i).getProId()));
            }
            model.addAttribute("products",products);
            return "shoppingcart";
        }
        else
            return "redirect:/user/login";

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




//    @GetMapping("/product_0001")  //跳转至1号商品页面
//    public String goProductonePage(Model model){
//
//        model.addAttribute("shoppingcart", new Shoppingcart());
//            return "/product_0001";
//    }

    @GetMapping("/details/{id}")//跳转到商品页面
    public String goProductPage(@PathVariable("id") String id,Model model){
        List<Product> products=  adminService.getProductsListByName(productService.findProductByPrimaryKey(id).getName());
        model.addAttribute("productsList",products);
        return "/product_0001";
    }

    @PostMapping("/addCart")//提交商品至购物车
    public String addShoppingCart(HttpServletRequest request,String proID) {
        String tUID= (String) request.getSession().getAttribute("USER_ID");
        if(tUID!=null){
            Shoppingcart shoppingcart=new Shoppingcart();
            shoppingcart.setProId(proID);
            shoppingcart.settUId(tUID);
            shoppingcartService.saveShoppingcart(shoppingcart);
            return "redirect:/product/shoppingcart";
        }
        else
            return "redirect:/user/login";


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
