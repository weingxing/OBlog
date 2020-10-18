package com.oxygen.oblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.dao.CommentMapper;
import com.oxygen.oblog.dao.ContentMapper;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.entity.Comment;
import com.oxygen.oblog.entity.Content;
import com.oxygen.oblog.service.CommentService;
import com.oxygen.oblog.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论服务类实现
 * @author Oxygen
 * @since 2020/09/27
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public boolean addComment(Comment comment) throws Exception {
        Content content = contentMapper.selectByPrimaryKey(comment.getCid());
        content.setCommentNum(content.getCommentNum()+1);
        comment.setStatus("reviewed");
        return commentMapper.insert(comment) > 0 &&
                contentMapper.updateByPrimaryKeySelective(content) > 0;
    }


    @Override
    public boolean deleteCommentByCoid(int coid) throws Exception {
        Comment comment = commentMapper.selectByPrimaryKey(coid);
        Content content = contentMapper.selectByPrimaryKey(comment.getCid());
        content.setCommentNum(content.getCommentNum()-1);
        return commentMapper.deleteByPrimaryKey(coid) > 0 &&
                contentMapper.updateByPrimaryKeySelective(content) > 0;
    }

    @Override
    public PageInfo<Comment> getPublicCommentPageByCid(int cid, PageRequest request) throws Exception {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> content = commentMapper.selectPageByCid(cid);
        return new PageInfo<Comment>(content);
    }

    @Override
    public PageInfo<Comment> getPublicCommentPage(PageRequest request) throws Exception {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> content = commentMapper.selectAllPublic();
        return new PageInfo<Comment>(content);
    }

    @Override
    public PageInfo<Comment> getReviewedCommentPage(PageRequest request) throws Exception {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> content = commentMapper.selectAllReviewed();
        return new PageInfo<Comment>(content);
    }

    @Override
    public PageInfo<Comment> getMyComment(String author, PageRequest request) throws Exception {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> content = commentMapper.selectMyComment(author);
        return new PageInfo<Comment>(content);
    }

    @Override
    public boolean passComment(int coid) throws Exception {
        Comment comment = commentMapper.selectByPrimaryKey(coid);
        comment.setStatus("public");
        return commentMapper.updateByPrimaryKeySelective(comment) > 0;
    }

    @Override
    public Comment getByCoid(int coid) throws Exception {
        return commentMapper.selectByPrimaryKey(coid);
    }

    @Override
    public boolean updateComment(Comment record) throws Exception {
        return commentMapper.updateByPrimaryKeySelective(record) > 0;
    }
}
