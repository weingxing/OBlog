package com.oxygen.oblog.dao;

import com.oxygen.oblog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Integer coid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer coid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAll();

    List<Comment> selectAllReviewed();

    List<Comment> selectAllPublic();

    List<Comment> selectPageByCid(int cid);

    List<Comment> selectMyComment(String author);
}