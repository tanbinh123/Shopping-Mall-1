package net.suncaper.springboot.service;


import net.suncaper.springboot.domain.Product;
import net.suncaper.springboot.domain.ProductExample;
import net.suncaper.springboot.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> listProduct() {
        //return ProductMapper.selectByExample(new ProductExample());
        return productMapper.selectByExample(new ProductExample());
    }

    public int saveProduct(Product product) {        //注册：将表单中的user对象插入数据库t_user表
        //if (product.getName() == null|| product.getName().equals("")) {
        // return productMapper.insert(product);
        //} else {
        //   return productMapper.updateByPrimaryKeySelective(product);
        // }
        return productMapper.insert(product);
    }

    public Product findProductByPrimaryKey(String id) {
        return productMapper.selectByPrimaryKey(id);
    }


    public void deleteProductById(String id) {   //删除用户
        productMapper.deleteByPrimaryKey(id);
    }

}
