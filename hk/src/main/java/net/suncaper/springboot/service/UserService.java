package net.suncaper.springboot.service;

import net.suncaper.springboot.domain.SearchUserRequest;
import net.suncaper.springboot.domain.User;
import net.suncaper.springboot.domain.UserExample;
import net.suncaper.springboot.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return userMapper.updateByPrimaryKey(user);
        }

    }
    public boolean login(User user) {
        if (userMapper.islogin(user)) {
            return true;
        } else {
            return false;
        }

    }



    public User findUserByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
    public void deleteUserById(String id) {
        userMapper.deleteByPrimaryKey(id);
    }
}