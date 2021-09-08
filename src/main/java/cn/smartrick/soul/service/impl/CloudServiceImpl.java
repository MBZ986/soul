package cn.smartrick.soul.service.impl;

import cn.smartrick.soul.entity.Cloud;
import cn.smartrick.soul.mapper.CloudMapper;
import cn.smartrick.soul.service.CloudService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CloudServiceImpl implements CloudService {

    @Autowired
    private CloudMapper cloudMapper;

    @Override
    public boolean addCloud(Cloud cloud) {
        return cloudMapper.insert(cloud) > 0;
    }

    @Override
    public boolean removeCloud(Integer id) {
        return cloudMapper.deleteById(id) > 0;
    }

    @Override
    public boolean modifyCloud(Cloud cloud) {
        return cloudMapper.updateById(cloud) > 0;
    }

    @Override
    public List<Cloud> queryCloudByUserId(Integer userId) {
        return cloudMapper.selectList(new QueryWrapper<Cloud>().eq("user_id",userId));
    }
}
