package cn.smartrick.soul.service;

import cn.smartrick.soul.entity.Cloud;

import java.util.Date;
import java.util.List;

public interface CloudService {
    boolean addCloud(Cloud cloud);
    boolean removeCloud(Integer id);
    boolean modifyCloud(Cloud cloud);
    List<Cloud> queryCloudByUserId(Integer userId);
}
