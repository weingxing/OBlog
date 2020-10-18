package com.oxygen.oblog.dao;

import com.oxygen.oblog.dto.ContentInfo;
import com.oxygen.oblog.entity.Content;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Integer cid);

    ContentInfo selectByCid(Integer cid);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);

    int updateByPrimaryKey(Content record);

    List<ContentInfo> selectAll();

    List<ContentInfo> selectPublicPage();

    List<ContentInfo> selectReviewedPage();

    List<ContentInfo> selectMyContent(Integer authorId);
}