package com.oxygen.oblog.dao;

import com.oxygen.oblog.entity.Right;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RightMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Right record);

    int insertSelective(Right record);

    Right selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Right record);

    int updateByPrimaryKey(Right record);

    List<Right> selectAll();
}