package cn.smartrick.soul.service.impl;

import cn.smartrick.soul.constant.CloudType;
import cn.smartrick.soul.constant.MediaType;
import cn.smartrick.soul.entity.Cloud;
import cn.smartrick.soul.entity.Poster;
import cn.smartrick.soul.mapper.CloudMapper;
import cn.smartrick.soul.mapper.PosterMapper;
import cn.smartrick.soul.service.PosterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PosterServiceImpl implements PosterService {

    @Autowired
    private PosterMapper posterMapper;
    @Autowired
    private CloudMapper cloudMapper;

    @Override
    public Page<Poster> queryReleaseByUser(Integer uid, Page<Poster> page) {
        return posterMapper.selectPage(page,new QueryWrapper<Poster>().eq("releaser",uid));
    }

    @Override
    public Page<Poster> queryAllPoster(Page<Poster> page) {
        return posterMapper.selectPage(page,new QueryWrapper<>());
    }

    @Override
    public boolean addPoster(Poster poster) {
        return posterMapper.insert(poster) > 0;
    }

    @Override
    public boolean removePoster(Integer id) {
        return posterMapper.deleteById(id) > 0;
    }

    @Override
    public boolean modifyPoster(Poster poster) {
        return posterMapper.updateById(poster) > 0;
    }

    @Override
    public boolean reading(Integer id, Integer uid) {
        if(posterMapper.reading(id)){
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.LOOK.getTypeCode());
            cloud.setMediaType(MediaType.POSTER_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Override
    public boolean liker(Integer id, Integer uid) {
        if(posterMapper.liker(id)){
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.LIKE.getTypeCode());
            cloud.setMediaType(MediaType.POSTER_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Override
    public boolean unliker(Integer id, Integer uid) {
        if(posterMapper.unliker(id)){
            return cloudMapper.delete(new QueryWrapper<Cloud>()
                    .eq("user_id",uid)
                    .eq("media_type",MediaType.POSTER_TYPE.getTypeCode())
                    .eq("cloud_type",CloudType.LIKE.getTypeCode())
                    .eq("media_id",id)) > 0;
        }
        return false;
    }

    @Override
    public boolean collect(Integer id, Integer uid) {
        if(posterMapper.collect(id)){
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.COLLECTION.getTypeCode());
            cloud.setMediaType(MediaType.POSTER_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Override
    public boolean uncollect(Integer id, Integer uid) {
        if(posterMapper.uncollect(id)){
            return cloudMapper.delete(new QueryWrapper<Cloud>()
                    .eq("user_id",uid)
                    .eq("media_type",MediaType.POSTER_TYPE.getTypeCode())
                    .eq("cloud_type",CloudType.COLLECTION.getTypeCode())
                    .eq("media_id",id)) > 0;
        }
        return false;
    }
}
