package com.oxygen.oblog.service;

import com.oxygen.oblog.entity.Options;

import java.util.List;
import java.util.Map;

/**
 * 配置信息服务类接口
 * @author Oxygen
 * @since 2020/09/24
 */
public interface OptionsService {

    /**
     * 取得网站标题
     * @return
     * @throws Exception
     */
    public String getTitle() throws Exception;

    /**
     * 取得全部设置信息
     * @return
     * @throws Exception
     */
    public Map<String ,String> getAll() throws Exception;

    /**
     * 取得网站描述
     * @return
     * @throws Exception
     */
    public String getDescription() throws Exception;

    /**
     * 取得网站关键字
     * @return
     * @throws Exception
     */
    public String getKeywords() throws Exception;

    /**
     * 取得网站地址
     * @return
     * @throws Exception
     */
    public String getSiteUrl() throws Exception;

    /**
     * 设置网站标题
     * @param title
     * @return
     * @throws Exception
     */
    public boolean setTitle(String title) throws Exception;

    /**
     * 设置网站描述
     * @param description
     * @return
     * @throws Exception
     */
    public boolean setDescription(String description) throws Exception;

    /**
     * 设置网站关键字
     * @param keywords
     * @return
     * @throws Exception
     */
    public boolean setKeywords(String keywords) throws Exception;

    /**
     * 设置网站地址
     * @param siteUrl
     * @return
     * @throws Exception
     */
    public boolean setSiteUrl(String siteUrl) throws Exception;

    /**
     * 取得背景音乐id
     * @return
     * @throws Exception
     */
    public String getMusic() throws Exception;

    /**
     * 设置背景音乐开关
     * @param status
     * @return
     * @throws Exception
     */
    public boolean setMusic(String status) throws Exception;

    /**
     * 取得latex渲染引擎开关
     * @return
     * @throws Exception
     */
    public String getLatex() throws Exception;

    /**
     * 设置latex渲染引擎开关
     * @param status
     * @return
     * @throws Exception
     */
    public boolean setLatex(String status) throws Exception;

    /**
     * 取得评论通知开关
     * @return
     * @throws Exception
     */
    public String getNotify() throws Exception;

    /**
     * 设置评论通知开关
     * @param status
     * @return
     * @throws Exception
     */
    public boolean setNotify(String status) throws Exception;

    /**
     * 根据name设置value
     * @param name
     * @param value
     * @return
     * @throws Exception
     */
    public boolean setOption(String name, String value) throws Exception;

    /**
     * 取得音乐链接
     * @return
     * @throws Exception
     */
    public String getMusicUrl() throws Exception;
}
