package com.oxygen.oblog.dao;

import com.oxygen.oblog.entity.Meta;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Meta record);

    int insertSelective(Meta record);

    Meta selectByPrimaryKey(Integer mid);

    Meta selectByName(String name);

    int updateByPrimaryKeySelective(Meta record);

    int updateByPrimaryKey(Meta record);

    List<Meta> selectAll();
}