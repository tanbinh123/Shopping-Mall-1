package net.suncaper.springboot.mapper;

import net.suncaper.springboot.domain.User;
import net.suncaper.springboot.domain.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void addTest() {
//        User user = new User();
//        user.setName("xxx");
//
//        userMapper.insert(user);

//        userMapper.deleteByPrimaryKey("1");
//        User user = new User();
//        user.setId("21edd1c8-a9be-11e9-b786-0a0027000004");
//        user.setName("tom");
//        userMapper.updateByPrimaryKey(user);
        UserExample ex = new UserExample();
        ex.createCriteria().andNameLike("%李%");
        List<User> users = userMapper.selectByExample(ex);
        System.out.println(users);
    }

}
