package net.suncaper.springboot.service;

import net.suncaper.springboot.domain.Order;
import net.suncaper.springboot.domain.OrderExample;
import net.suncaper.springboot.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<Order> selesctBytUId(String userID) {
        OrderExample ex=new OrderExample();
        ex.createCriteria()
                .andTUIdEqualTo(userID);
        return orderMapper.selectByExample(ex);
    }
}
