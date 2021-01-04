package com.mapper;

import com.po.Itripuserlinkuser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface ItripuserlinkuserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Itripuserlinkuser record);

    int insertSelective(Itripuserlinkuser record);

    Itripuserlinkuser selectByPrimaryKey(@Param(value = "id") Long id);//根据用户id查询常用联系人

    int updateByPrimaryKeySelective(Itripuserlinkuser record);

    int updateByPrimaryKey(Itripuserlinkuser record);

    public List<Itripuserlinkuser> getItripuserLinkuserMap(Map<String,Object> param) throws Exception;
}