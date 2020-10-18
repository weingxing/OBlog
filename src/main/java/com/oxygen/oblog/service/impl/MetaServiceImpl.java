package com.oxygen.oblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.dao.MetaMapper;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.entity.Meta;
import com.oxygen.oblog.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 元素据服务类实现
 * @author Oxygen
 * @since 2020/09/24
 */
@Service
public class MetaServiceImpl implements MetaService {
    @Autowired
    private MetaMapper mapper;

    @Override
    public boolean addMeta(Meta meta) throws Exception {
        Meta m = mapper.selectByName(meta.getMetaName());
        if (m.getMetaName() != null)
            return mapper.insert(meta) > 0;
        else
//            已经存在，直接返回true
            return true;
    }

    @Override
    public Meta getMetaByMid(int mid) throws Exception {
        return mapper.selectByPrimaryKey(mid);
    }

    @Override
    public boolean deleteMetaByMid(int mid) throws Exception {
        return mapper.deleteByPrimaryKey(mid) > 0;
    }

    @Override
    public Meta getMetaByName(String name) throws Exception {
        return mapper.selectByName(name);
    }

    @Override
    public boolean updateMeta(Meta meta) throws Exception {
        return mapper.updateByPrimaryKey(meta) > 0;
    }

    @Override
    public PageInfo<Meta> getMetaPage(PageRequest request) throws Exception {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Meta> content = mapper.selectAll();
        return new PageInfo<>(content);
    }
}
