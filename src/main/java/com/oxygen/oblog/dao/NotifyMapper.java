package com.oxygen.oblog.dao;

import com.oxygen.oblog.entity.Notify;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyMapper {
    int deleteByPrimaryKey(Integer nid);

    int insert(Notify record);

    int insertSelective(Notify record);

    Notify selectByPrimaryKey(Integer nid);

    int updateByPrimaryKeySelective(Notify record);

    int updateByPrimaryKey(Notify record);
}