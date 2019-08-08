package net.suncaper.springboot.service;

import net.suncaper.springboot.domain.Address;
import net.suncaper.springboot.domain.AddressExample;
import net.suncaper.springboot.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
        @Autowired
        private AddressMapper addressMapper;


    public void saveAddress(Address address) {
        addressMapper.insert(address);
        return;
    }

    public List<Address> selectByTUID(String tuid) {
        AddressExample ex=new AddressExample();
        ex.createCriteria()
                .andTUIdEqualTo(tuid);
        return addressMapper.selectByExample(ex);
    }

    public Address selectByPrimaryKey(String checkedaddress) {
       return addressMapper.selectByPrimaryKey(checkedaddress);
    }

    public void deleteById(String id) {
        addressMapper.deleteByPrimaryKey(id);
        return;
    }

    public void upDateById(Address address) {
        addressMapper.updateByPrimaryKeySelective(address);
    }
}
