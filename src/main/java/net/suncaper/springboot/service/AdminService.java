package net.suncaper.springboot.service;



import net.suncaper.springboot.domain.Admin;
import net.suncaper.springboot.domain.AdminExample;
import net.suncaper.springboot.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    public List login(Admin admin) {       //登录：判断输入的密码是否正确
        AdminExample ex=new AdminExample();
        ex.createCriteria()
                .andAdminidEqualTo(admin.getAdminid())
                .andPasswordEqualTo(admin.getPassword());
        List<Admin> admins = adminMapper.selectByExample(ex);

        return admins;
    }


//    public List<Product> getProductsListByName(String name) {
//        ProductExample ex=new ProductExample();
//        ex.createCriteria().andNameLike("%"+name+"%");
//        List<Product> products=productMapper.selectByExample(ex);
//        return products;
//    }
}
