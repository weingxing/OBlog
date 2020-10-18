package com.oxygen.oblog.dao;

import com.oxygen.oblog.entity.Options;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionsMapper {
    int deleteByPrimaryKey(String name);

    int insert(Options record);

    int insertSelective(Options record);

    Options selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Options record);

    int updateByPrimaryKey(Options record);

    List<Options> selectAll();
}