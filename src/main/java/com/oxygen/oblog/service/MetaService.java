package com.oxygen.oblog.service;

import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.entity.Meta;

/**
 * 元数据服务类接口
 * @author Oxygen
 * @since 2020/09/24
 */
public interface MetaService {

    /**
     * 添加元数据
     * @param meta
     * @return
     * @throws Exception
     */
    public boolean addMeta(Meta meta) throws Exception;

    /**
     * 根据mid取得元数据
     * @param mid
     * @return
     * @throws Exception
     */
    public Meta getMetaByMid(int mid) throws Exception;

    /**
     * 根据名字取得元数据
     * @param name
     * @return
     * @throws Exception
     */
    public Meta getMetaByName(String name) throws Exception;

    /**
     * 根据mid删除元数据
     * @param mid
     * @return
     * @throws Exception
     */
    public boolean deleteMetaByMid(int mid) throws Exception;

    /**
     * 更新元数据
     * @param meta
     * @return
     * @throws Exception
     */
    public boolean updateMeta(Meta meta)throws Exception;

    /**
     * 分页取得数据
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    public PageInfo<Meta> getMetaPage(PageRequest request) throws Exception;
}
