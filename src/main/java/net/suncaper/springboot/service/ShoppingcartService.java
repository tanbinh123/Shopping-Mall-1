package net.suncaper.springboot.service;
import net.suncaper.springboot.domain.*;
import net.suncaper.springboot.mapper.ShoppingcartMapper;
import net.suncaper.springboot.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingcartService {
    @Autowired
    private ShoppingcartMapper shoppingcartMapper;

    public List<Shoppingcart> listShoppingcart() {
        return shoppingcartMapper.selectByExample(new ShoppingcartExample());
    }

//    public List<Product> listShoppingcart2() {
//        return shoppingcartMapper.addShoppingcart();
//    }

    public Boolean saveShoppingcart(Shoppingcart shoppingcart) {
        shoppingcartMapper.insert(shoppingcart);
        return true;
    }

    public Shoppingcart findShoppingcartByPrimaryKey(String id) {
        return shoppingcartMapper.selectByPrimaryKey(id);
    }

    public void deleteShoppingcartById(String id) {   //删除用户
        shoppingcartMapper.deleteByPrimaryKey(id);
    }


    public List<Shoppingcart> getProid(ShoppingcartExample example) {
        return shoppingcartMapper.selectByExample(example);
    }

    public List<Shoppingcart> selectByUserID(String userID) {
        ShoppingcartExample ex = new ShoppingcartExample();
        ex.createCriteria()
                .andTUIdEqualTo(userID);
        return shoppingcartMapper.selectByExample(ex);
    }

    public void deleteShoppingcartByProID(String id) {
        shoppingcartMapper.deleteByProID(id);
    }

    public Shoppingcart findShoppingcartByProID(String proId) {
        return shoppingcartMapper.selectByProID(proId);
    }

    public Boolean updateQuantity(Shoppingcart shoppingcart) {
        int quantity=shoppingcart.getQuantity();
        String proID=shoppingcart.getProId();
        shoppingcartMapper.updateQuantity(quantity,proID);
        return true;
    }



}