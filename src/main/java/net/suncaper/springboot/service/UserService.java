package net.suncaper.springboot.service;

import net.suncaper.springboot.domain.*;
import net.suncaper.springboot.mapper.ProductMapper;
import net.suncaper.springboot.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void add() {     //示例代码，这里没有用到
        SearchUserRequest request = new SearchUserRequest();
        request.setKeyword("%李%");
        request.setAge(10);
        List<User> results = userMapper.search(null, 20);
        System.out.println(results);
    }

    public List<User> listUser() {           //用户管理：调用userMapper里的selectByExample接口方法返回所有t_user中的元组
        return userMapper.selectByExample(new UserExample());
    }
    //用户管理：调用userMapper里的selectByExample接口方法返回所有t_user中的元组

    public int saveUser(User user) {        //注册：将表单中的user对象插入数据库t_user表
        if (user.getId() == null|| user.getId().equals("")) {
            return userMapper.insert(user);
        } else {
            return userMapper.updateByPrimaryKeySelective(user);
        }
    }



    public User login(User user) {       //登录：判断输入的用户名密码是否正确
       User user1= userMapper.selectByName(user.getName());
        if (user1!=null && user1.getPassword().equals(user.getPassword())){
            return user1;
        } else {
            return null;
        }
    }
    public User findUserByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
    public void deleteUserById(String id) {   //删除用户
        userMapper.deleteByPrimaryKey(id);
    }
}

