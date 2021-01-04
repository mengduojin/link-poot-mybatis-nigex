package com.service;

import com.po.Itripuserlinkuser;

import java.util.List;
import java.util.Map;

public interface ItripuserLinkuserService {
    //模糊查询
    public List<Itripuserlinkuser> getItripuserLinkuserMap(Map<String,Object> param) throws Exception;
}
