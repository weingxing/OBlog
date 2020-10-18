package com.oxygen.oblog.service.impl;

import com.oxygen.oblog.dao.NotifyMapper;
import com.oxygen.oblog.dao.OptionsMapper;
import com.oxygen.oblog.entity.Notify;
import com.oxygen.oblog.service.NotifyService;
import com.oxygen.oblog.util.SendMailThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 通知服务类实现
 * @author Oxygen
 * @since 2020/09/27
 */
@Service
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    private NotifyMapper notifyMapper;
    @Autowired
    private OptionsMapper optionsMapper;

    @Override
    public Notify getInfo() throws Exception {
        return notifyMapper.selectByPrimaryKey(1);
    }

    @Override
    public boolean updateInfo(Notify notify) throws Exception {
        notify.setNid(1);
        if(notifyMapper.updateByPrimaryKey(notify) > 0)
            return true;
        else
            return notifyMapper.insert(notify) > 0;
    }

    @Override
    @Async
    public void sendNotify(String title, String text) throws Exception {
        Notify notify = getInfo();
        String siteTitle = optionsMapper.selectByPrimaryKey("title").getValue();
        SendMailThread.sendMail(notify, siteTitle + "-" + title, text);
    }
}
