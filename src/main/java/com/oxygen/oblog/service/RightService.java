package com.oxygen.oblog.service;

import com.oxygen.oblog.entity.Right;

import java.util.List;

/**
 * 权限服务类接口
 * @author Oxygen
 * @since 2020/09/27
 */
public interface RightService {
    /**
     * 添加权限
     * @param right
     * @return
     * @throws Exception
     */
    public boolean addRight(Right right) throws Exception;

    /**
     * 删除权限
     * @param rid
     * @return
     * @throws Exception
     */
    public boolean deleteRight(int rid) throws Exception;

    /**
     * 更新权限信息
     * @param right
     * @return
     * @throws Exception
     */
    public boolean updateRight(Right right) throws Exception;

    /**
     * 根据rid取得权限信息
     * @param rid
     * @return
     * @throws Exception
     */
    public Right getRightByRid(int rid) throws Exception;

    /**
     * 取得全部权限信息
     * @return
     */
    public List<Right> getAllRight();
}
