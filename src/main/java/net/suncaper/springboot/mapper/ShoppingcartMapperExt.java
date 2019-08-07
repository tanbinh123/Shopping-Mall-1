package net.suncaper.springboot.mapper;

import net.suncaper.springboot.domain.Product;
import net.suncaper.springboot.domain.Shoppingcart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingcartMapperExt {
    List<Product> addShoppingcart(@Param("id") String id);

    int deleteByProID(String id);

    Shoppingcart selectByProID(String proID);

    int updateQuantity( int quantity,String proID);
}
