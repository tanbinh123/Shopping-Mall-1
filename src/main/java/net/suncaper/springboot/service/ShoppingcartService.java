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

    public Boolean saveShoppingcart(Shoppingcart shoppingcart) {
        shoppingcartMapper.insert(shoppingcart);
    return true;
    }
}
