package net.suncaper.springboot.service;

import net.suncaper.springboot.domain.SearchUserRequest;
import net.suncaper.springboot.domain.User;
import net.suncaper.springboot.domain.UserExample;
import net.suncaper.springboot.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void add() {
        SearchUserRequest request = new SearchUserRequest();
        request.setKeyword("%李%");
        request.setAge(10);
        List<User> results = userMapper.search(null, 20);
        System.out.println(results);

    }
    public List<User> listUser() {
        return userMapper.selectByExample(new UserExample());
    }
    public int saveUser(User user) {
        if (user.getId() == null|| user.getId().equals("")) {
            return userMapper.insert(user);
        } else {
            return userMapper.updateByPrimaryKeySelective(user);
        }

    }
    public User login(User user) {
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
    public void deleteUserById(String id) {
        userMapper.deleteByPrimaryKey(id);
    }
}

