package com.oxygen.oblog.service;

import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.entity.Comment;


/**
 * 评论服务类接口
 * @author Oxygen
 * @since 2020/09/27
 */
public interface CommentService {
    /**
     * 添加评论
     * @param comment
     * @return
     * @throws Exception
     */
    public boolean addComment(Comment comment) throws Exception;

    /**
     * 根据文章cid获取评论
     * @param cid
     * @return
     * @throws Exception
     */
//    public List<Comment> getAllCommentByCid(int cid) throws Exception;

    /**
     * 根据评论coid删除评论
     * @param coid
     * @return
     * @throws Exception
     */
    public boolean deleteCommentByCoid(int coid) throws Exception;

    /**
     * 根据cid分页获取已发布评论
     * @param cid
     * @return
     * @throws Exception
     */
    public PageInfo<Comment> getPublicCommentPageByCid(int cid, PageRequest request) throws Exception;

    /**
     * 分页获取所有已发布评论
     * @return
     * @throws Exception
     */
    public PageInfo<Comment> getPublicCommentPage(PageRequest request) throws Exception;

    /**
     * 分页获取所有待审核评论
     * @return
     * @throws Exception
     */
    public PageInfo<Comment> getReviewedCommentPage(PageRequest request) throws Exception;

    /**
     * 取得我的评论
     * @param author
     * @param request
     * @return
     * @throws Exception
     */
    public PageInfo<Comment> getMyComment(String author, PageRequest request) throws Exception;

    /**
     * 评论通过审核
     * @param coid
     * @return
     * @throws Exception
     */
    public boolean passComment(int coid) throws Exception;

    /**
     * 通过coid取得评论
     * @param coid
     * @return
     * @throws Exception
     */
    public Comment getByCoid(int coid) throws Exception;

    /**
     * 更新评论
     * @param record
     * @return
     * @throws Exception
     */
    public boolean updateComment(Comment record) throws Exception;
}
