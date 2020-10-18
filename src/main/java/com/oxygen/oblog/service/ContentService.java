package com.oxygen.oblog.service;

import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.dto.ContentInfo;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.entity.Content;

import java.util.List;

/**
 * 文章服务类接口
 * @author Oxygen
 * @since 2020/09/24
 */
public interface ContentService {
    /**
     * 取得所有文章
     * @return
     * @throws Exception
     */
    public List<ContentInfo> getAllContent() throws Exception;

    /**
     * 分页取得已发布的文章
     * @return
     */
    public PageInfo<ContentInfo> getPublicContentByPage(PageRequest pageRequest) throws Exception;

    /**
     * 分页取得待审核的文章
     * @return
     */
    public PageInfo<ContentInfo> getReviewedContentByPage(PageRequest pageRequest) throws Exception;

    /**
     * 添加文章
     * @param content
     * @return
     * @throws Exception
     */
    public boolean addContent(Content content) throws Exception;

    /**
     * 根据cid删除文章
     * @param cid
     * @return
     * @throws Exception
     */
    public boolean deleteContentByCid(int cid) throws Exception;

    /**
     * 根据cid修改文章
     * @param content
     * @return
     * @throws Exception
     */
    public boolean updateContentByCid(Content content) throws Exception;

    /**
     * 根据cid获取文章信息
     * @param cid
     * @return
     * @throws Exception
     */
    public ContentInfo getContentByCid(int cid) throws Exception;

    /**
     * 根据uid获取文章
     * @param authorId
     * @return
     * @throws Exception
     */
    public PageInfo<ContentInfo> getMyContentByPage(int authorId, PageRequest request) throws Exception;

    /**
     * 类别转换
     * @param contentInfo
     * @return
     * @throws Exception
     */
    public Content contentInfo2Content(ContentInfo contentInfo) throws Exception;

    /**
     * 审核文章通过
     * @param content
     * @return
     * @throws Exception
     */
    public boolean pass(Content content) throws Exception;
}
