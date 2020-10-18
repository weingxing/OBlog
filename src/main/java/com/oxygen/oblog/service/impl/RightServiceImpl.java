package com.oxygen.oblog.service.impl;

import com.oxygen.oblog.dao.RightMapper;
import com.oxygen.oblog.entity.Right;
import com.oxygen.oblog.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限服务类实现
 * @author Oxygen
 * @since 2020/09/27
 */
@Service
public class RightServiceImpl implements RightService {
    @Autowired
    private RightMapper rightMapper;

    @Override
    public boolean addRight(Right right) throws Exception {
        return rightMapper.insert(right) > 0;
    }

    @Override
    public boolean deleteRight(int rid) throws Exception {
        return rightMapper.deleteByPrimaryKey(rid) > 0;
    }

    @Override
    public boolean updateRight(Right right) throws Exception {
        return rightMapper.updateByPrimaryKey(right) > 0;
    }

    @Override
    public Right getRightByRid(int rid) throws Exception {
        return rightMapper.selectByPrimaryKey(rid);
    }

    @Override
    public List<Right> getAllRight() {
        return rightMapper.selectAll();
    }
}
