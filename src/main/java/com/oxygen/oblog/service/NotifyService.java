package com.oxygen.oblog.service;

import com.oxygen.oblog.entity.Notify;

/**
 * 通知服务类接口
 */
public interface NotifyService {

    /**
     * 取得通知smpt信息
     * @return
     * @throws Exception
     */
    public Notify getInfo() throws Exception;

    /**
     * 更新smpt信息
     * @param notify
     * @return
     * @throws Exception
     */
    public boolean updateInfo(Notify notify) throws Exception;

    /**
     * 发送通知
     * @param title
     * @param text
     * @return
     * @throws Exception
     */
    public void sendNotify(String title, String text) throws Exception;
}
