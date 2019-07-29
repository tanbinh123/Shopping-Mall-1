package net.suncaper.springboot.service;

import net.suncaper.springboot.domain.*;
import net.suncaper.springboot.mapper.SkuMapper;
import net.suncaper.springboot.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import redis.clients.jedis.Jedis;


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
        if (user.getId() == null || user.getId().equals("")) {
            return userMapper.insert(user);
        } else {
            return userMapper.updateByPrimaryKeySelective(user);
        }

    }

    public User login(User user) {
        User user1 = userMapper.selectByName(user.getName());
        if (user1 != null && user1.getPassword().equals(user.getPassword())) {

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


    //保存购物车到Redis中
    public static void insertBuyerCartToRedis(BuyerCart buyerCart, String username) {
        Jedis jedis = new Jedis();
        List<BuyerItem> items = buyerCart.getItems();
        if (items.size() > 0) {
            //redis中保存的是skuId 为key , amount 为value的Map集合
            Map<String, String> hash = new HashMap<String, String>();
            for (BuyerItem item : items) {
                //判断是否有同款
                if (jedis.hexists("buyerCart:" + username, String.valueOf(item.getSku().getId()))) {
                    jedis.hincrBy("buyerCart:" + username, String.valueOf(item.getSku().getId()), item.getAmount());
                } else {
                    hash.put(String.valueOf(item.getSku().getId()), String.valueOf(item.getAmount()));
                }
            }
            if (hash.size() > 0) {
                jedis.hmset("buyerCart:" + username, hash);
            }
        }
    }

















    //获取
    public static String getAttributterForUsername(String jessionId){
        Jedis jedis = new Jedis();
        String value = jedis.get(jessionId + ":USER_NAME");
        if(null != value){
            //计算session过期时间是 用户最后一次请求开始计时.
            jedis.expire(jessionId + ":USER_NAME", 6000);
            return value;
        }
        return null;
    }





    //取出Redis中购物车
    public static BuyerCart selectBuyerCartFromRedis(String username){
        Jedis jedis = new Jedis();
        BuyerCart buyerCart = new BuyerCart();
        //获取所有商品, redis中保存的是skuId 为key , amount 为value的Map集合
        Map<String, String> hgetAll = jedis.hgetAll("buyerCart:"+username);
        Set<Map.Entry<String, String>> entrySet = hgetAll.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            //entry.getKey(): skuId
            Sku sku = new Sku();
            sku.setId(Long.parseLong(entry.getKey()));
            BuyerItem buyerItem = new BuyerItem();
            buyerItem.setSku(sku);
            //entry.getValue(): amount
            buyerItem.setAmount(Integer.parseInt(entry.getValue()));
            //添加到购物车中
            buyerCart.addItem(buyerItem);
        }

        return buyerCart;
    }

    //向购物车中的购物项 添加相应的数据, 通过skuId 查询sku对象, 颜色对象, 商品对象
    public static Sku selectSkuById(Long skuId){
        Sku sku = (Sku) SkuMapper.selectByPrimaryKey(skuId);
        //颜色
        sku.setColor(SkuMapper.selectByPrimaryKey(sku.getColorId()));
        //添加商品信息
        sku.setProductId(SkuMapper.selectByPrimaryKey(sku.getProductId()));
        return sku;
    }

    //从购物车中取出指定商品
    public static BuyerCart selectBuyerCartFromRedisBySkuIds(String[] skuIds, String username){
        BuyerCart buyerCart = new BuyerCart();
        //获取所有商品, redis中保存的是skuId 为key , amount 为value的Map集合
        Jedis jedis = new Jedis("192.168.10.1", 6379);


        Map<String, String> hgetAll = jedis.hgetAll("buyerCart:"+username);
        if (null != hgetAll && hgetAll.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = hgetAll.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                for (String skuId : skuIds) {
                    if (skuId.equals(entry.getKey())) {
                       // entry.getKey():skuId
                        Sku sku = new Sku();
                        sku.setId(Long.parseLong(entry.getKey()));
                        BuyerItem buyerItem = new BuyerItem();
                        buyerItem.setSku(sku);
                        //entry.getValue(): amount;
                        buyerItem.setAmount(Integer.parseInt(entry.getValue()));
                        //添加到购物车中
                        buyerCart.addItem(buyerItem);
                    }
                }
            }
        }

        return buyerCart;
    }


}

