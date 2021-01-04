package com.service.Impl;


import com.mapper.ItripuserlinkuserMapper;
import com.po.Itripuserlinkuser;
import com.service.ItripuserLinkuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
@Transactional
public class ItripuserLinkuseServiceImpl implements ItripuserLinkuserService {
    @Autowired
    private ItripuserlinkuserMapper itripuserlinkusermapper;
    @Override
    public List<Itripuserlinkuser> getItripuserLinkuserMap(Map<String, Object> param) throws Exception {
        System.out.println("service"+itripuserlinkusermapper.getItripuserLinkuserMap(param));
        return itripuserlinkusermapper.getItripuserLinkuserMap(param);
    }
}
