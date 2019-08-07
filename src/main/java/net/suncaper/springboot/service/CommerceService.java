package net.suncaper.springboot.service;

import net.suncaper.springboot.domain.Commerce;
import net.suncaper.springboot.domain.CommerceExample;
import net.suncaper.springboot.mapper.CommerceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommerceService {
    @Autowired
    private CommerceMapper commerceMapper;

    public List<Commerce> selectBytOId(String id) {
        CommerceExample ex=new CommerceExample();
        ex.createCriteria()
                .andTOIdEqualTo(id);
        return commerceMapper.selectByExample(ex);
    }
}
