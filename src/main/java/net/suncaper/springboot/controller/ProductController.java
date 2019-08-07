package net.suncaper.springboot.controller;
import com.sun.org.apache.xpath.internal.operations.Or;
import net.suncaper.springboot.domain.*;
import net.suncaper.springboot.mapper.AddressMapper;
import net.suncaper.springboot.mapper.CommerceMapper;
import net.suncaper.springboot.mapper.OrderMapper;
import net.suncaper.springboot.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.java2d.pipe.AAShapePipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CommerceMapper commerceMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CommerceService commerceService;

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
    public String ShoppingCartPage(HttpServletRequest request, Model model ) {
        String userID= (String) request.getSession().getAttribute("USER_ID");
        if (userID!=null){
            List<Shoppingcart> shoppingcarts=shoppingcartService.selectByUserID(userID);
            List<Product> products =new ArrayList<>();
            for(int i=0;i<shoppingcarts.size();i++){
                products.add(productService.findProductByPrimaryKey(shoppingcarts.get(i).getProId()));
            }
            model.addAttribute("products",products);
            request.getSession().setAttribute("CART_num",shoppingcartService.selectByUserID(userID).size());
            return "shoppingcart";
        }
        else
            return "redirect:/user/login";

    }



    @PostMapping("/shoppingcart")//提交购物车，生成订单
    public String ChangeQuantity(HttpServletRequest request,String[] proID,int[] quantity){
        if(proID.length==quantity.length&&proID.length>0){
            String userID= (String) request.getSession().getAttribute("USER_ID");
            Order order=new Order();
            order.settUId(userID);
            order.setOrderStatus("待付款");
            orderMapper.insert(order);
            BigDecimal totalPrice=new BigDecimal(0);
            String oID=order.getId();
            for(int i=0;i<proID.length;i++){
                Commerce commerce=new Commerce();
                commerce.settOId(oID);
                Product product=productService.findProductByPrimaryKey(proID[i]);
                commerce.setProId(product.getId());
                commerce.setProName(product.getName());
                commerce.setProType(product.getProductType());
                commerce.setQuantity(quantity[i]);
                commerce.setPreprice(productService.findProductByPrimaryKey(proID[i]).getPrice().multiply(BigDecimal.valueOf(quantity[i])));
                totalPrice=totalPrice.add(commerce.getPreprice());
                commerceMapper.insert(commerce);
                shoppingcartService.deleteBytUIDAndProID(userID,proID[i]);
            }
            order.setTotalPrice(totalPrice.add(BigDecimal.valueOf(10)));
            orderMapper.updateByPrimaryKey(order);
        }


        return "redirect:/product/checkoutlist";
    }

    @GetMapping("/checkoutlist")//进入我的订单
    public String goMyCheckoutPage(HttpServletRequest request, Model model){
        String userID= (String) request.getSession().getAttribute("USER_ID");
        if(userID!=null){
            List<Order> orders=orderService.selesctBytUId(userID);  //获得此位用户的所有订单行
            model.addAttribute("orders",orders);  //赋值给orders
//            for(int i=0;i<orders.size();i++){
//                String goID=orders.get(i).getId();
//                model.addAttribute(goID,commerceService.selectBytOId(orders.get(i).getId()));
//            }
            return "checkoutlist";
        }
        else
            return "redirect:/user/login";
    }



    @GetMapping("/checkout/{id}")      //订单页面的实验
    public String goCheckOutPage(@PathVariable("id") String id,HttpServletRequest request, Model model) {
        String tuid= (String) request.getSession().getAttribute("USER_ID");
        Order order=orderMapper.selectByPrimaryKey(id);
        List<Commerce> commerces=commerceService.selectBytOId(id);
        model.addAttribute("commmerces",commerces);
        model.addAttribute("order",order);
        List<Address> addresses= addressService.selectByTUID(tuid);
        model.addAttribute("addaddress",new Address());
        model.addAttribute("addresses",addresses);
        return "checkout";
    }

    @PostMapping("/topay")//去付款
    public String goToPay(String checkedaddress, String order){
        Address address= addressService.selectByPrimaryKey(checkedaddress);
        Order orderexe=orderService.selectByPrimaryKey(order);
        orderexe.settUName(address.gettUName());
        orderexe.setPhone(address.getPhone());
        orderexe.setOrderStatus("待发货");
        orderexe.setAddress(address.getProvince()+address.getCity()+address.getCounty()+address.getTown()+address.getDetail());
        orderMapper.updateByPrimaryKey(orderexe);
        return "redirect:/product/checkoutlist";
    }


    @PostMapping("/addAddress")//添加地址
    public String addAddressInfo(HttpServletRequest request, Address address, String orderID){
        String tuid= (String) request.getSession().getAttribute("USER_ID");
        if (tuid!=null){
            address.settUId(tuid);
            addressService.saveAddress(address);
            return "redirect:/product/checkout/"+orderID;
        }
        else
            return "redirect:/user/login";

    }



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
            if(shoppingcartService.selectShoppingcartByProIDAndTUID(proID,tUID)==false){
                Shoppingcart shoppingcart=new Shoppingcart();
                shoppingcart.setProId(proID);
                shoppingcart.settUId(tUID);
                shoppingcartService.saveShoppingcart(shoppingcart);
                request.getSession().setAttribute("CART_num",shoppingcartService.selectByUserID(tUID).size());
            }
            return "redirect:/product/details/"+proID;
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
