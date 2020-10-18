package com.oxygen.oblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.dao.ContentMapper;
import com.oxygen.oblog.dao.MetaMapper;
import com.oxygen.oblog.dao.UserMapper;
import com.oxygen.oblog.dto.ContentInfo;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.entity.Content;
import com.oxygen.oblog.entity.Meta;
import com.oxygen.oblog.entity.User;
import com.oxygen.oblog.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章服务类实现
 * @author Oxyge
 * @since 2020/09/25
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private MetaMapper metaMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<ContentInfo> getAllContent() throws Exception {
        return contentMapper.selectAll();
    }

    @Override
    public PageInfo<ContentInfo> getPublicContentByPage(PageRequest pageRequest) throws Exception {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ContentInfo> content = contentMapper.selectPublicPage();
        return new PageInfo<ContentInfo>(content);
    }

    @Override
    public PageInfo<ContentInfo> getReviewedContentByPage(PageRequest pageRequest) throws Exception {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ContentInfo> content = contentMapper.selectReviewedPage();
        return new PageInfo<ContentInfo>(content);
    }

    @Override
    public boolean addContent(Content content) throws Exception {
        return contentMapper.insert(content) > 0;
    }

    @Override
    public boolean deleteContentByCid(int cid) throws Exception {
        return contentMapper.deleteByPrimaryKey(cid) > 0;
    }

    @Override
    public boolean updateContentByCid(Content content) throws Exception {
        return contentMapper.updateByPrimaryKeyWithBLOBs(content) > 0;
    }

    @Override
    public ContentInfo getContentByCid(int cid) throws Exception {
        return contentMapper.selectByCid(cid);
    }

    @Override
    public PageInfo<ContentInfo> getMyContentByPage(int authorId, PageRequest request) throws Exception {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ContentInfo> content = contentMapper.selectMyContent(authorId);
        return new PageInfo<ContentInfo>(content);
    }

    @Override
    public boolean pass(Content content) throws Exception {
//        content.setStatus("public");
        return contentMapper.updateByPrimaryKeyWithBLOBs(content) > 0;
    }

    @Override
    public Content contentInfo2Content(ContentInfo contentInfo) throws Exception {
        Content record = new Content();
        record.setCid(contentInfo.getCid());
        record.setCreated(contentInfo.getCreated());
        record.setModified(contentInfo.getModified());
        record.setCommentNum(0);
        record.setStatus("reviewed");
        record.setTitle(contentInfo.getTitle());
        record.setText(contentInfo.getText());

        Meta m = metaMapper.selectByName(contentInfo.getMeta());
        User u = userMapper.selectByName(contentInfo.getAuthor());
        int authorId;

        if(m != null) {
            record.setMetaId(m.getMid());
        }
        else {
            Meta meta = new Meta();
            meta.setMetaName(contentInfo.getMeta());
            if (metaMapper.insert(meta) > 0)
                record.setMetaId(metaMapper.selectByName(contentInfo.getMeta()).getMid());
        }

        if(u != null) {
            record.setAuthorId(u.getUid());
            if (u.getUid() == 1)
                record.setStatus("public");
        }
        else
            record.setAuthorId(0);

        return record;
    }
}
