package cn.smartrick.soul.service.impl;

import cn.smartrick.soul.constant.CloudType;
import cn.smartrick.soul.constant.MediaType;
import cn.smartrick.soul.dto.MediaSortPageDto;
import cn.smartrick.soul.entity.Cloud;
import cn.smartrick.soul.entity.Music;
import cn.smartrick.soul.entity.Video;
import cn.smartrick.soul.mapper.CloudMapper;
import cn.smartrick.soul.mapper.SortMapper;
import cn.smartrick.soul.mapper.VideoMapper;
import cn.smartrick.soul.service.VideoService;
import cn.smartrick.soul.util.StringUtil;
import cn.smartrick.soul.util.UploadFileQiniu;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private SortMapper sortMapper;
    @Autowired
    private CloudMapper cloudMapper;
    @Autowired
    private UploadFileQiniu fileQiniu;

    @Override
    public Page<Video> queryReleaseByUser(Integer uid, Page<Video> page) {
        return parse(videoMapper.selectPage(page,new QueryWrapper<Video>().eq("releaser",uid)));
    }

    @Override
    public Page<Video> queryVideoBySortId(Integer sortId,Page<Video> page) {
        MediaSortPageDto mediaSortPageDto =
                new MediaSortPageDto(MediaType.VIDEO_TYPE.getTypeCode(),
                        sortId,
                        (page.getCurrent()-1)*page.getSize(),
                        page.getSize());
        return parse(page.setRecords(sortMapper.selectVideosBySort(mediaSortPageDto)));
    }

    @Override
    public Page<Video> queryAllVideo(Page<Video> page) {
        List<Video> videos = videoMapper.selectPageVideo((page.getCurrent() - 1) * page.getSize(), page.getSize());
        return parse(page.setRecords(videos));
    }

    @Override
    public boolean addVideo(Video video) {
        return videoMapper.insert(video) > 0;
    }

    @Override
    public boolean modifyVideo(Video video) {
        return videoMapper.updateVideoById(video);
    }

    @Override
    public boolean removeVideo(Integer id) {
        return videoMapper.deleteById(id) > 0;
    }

    @Transactional
    @Override
    public boolean playing(Integer id, Integer uid) {
        if(videoMapper.playing(id)){
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.PLAYING.getTypeCode());
            cloud.setMediaType(MediaType.VIDEO_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean liker(Integer id, Integer uid) {
        if(videoMapper.liker(id)){
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.LIKE.getTypeCode());
            cloud.setMediaType(MediaType.VIDEO_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean unliker(Integer id, Integer uid) {
        if(videoMapper.unliker(id)){
            return cloudMapper.delete(new QueryWrapper<Cloud>()
                    .eq("user_id",uid)
                    .eq("media_type",MediaType.VIDEO_TYPE.getTypeCode())
                    .eq("cloud_type",CloudType.LIKE.getTypeCode())
                    .eq("media_id",id)) > 0;
        }
        return false;
    }

    @Override
    public boolean collect(Integer id, Integer uid) {
        if(videoMapper.collect(id)){
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.COLLECTION.getTypeCode());
            cloud.setMediaType(MediaType.VIDEO_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Override
    public boolean uncollect(Integer id, Integer uid) {
        if(videoMapper.uncollect(id)){
            return cloudMapper.delete(new QueryWrapper<Cloud>()
                    .eq("user_id",uid)
                    .eq("media_type",MediaType.VIDEO_TYPE.getTypeCode())
                    .eq("cloud_type",CloudType.COLLECTION.getTypeCode())
                    .eq("media_id",id)) > 0;
        }
        return false;
    }

    private Page<Video> parse(Page page) {
        ((List<Video>) page.getRecords()).forEach(obj -> {
            if(StringUtil.isStorage(obj.getCoverUrl()))
                obj.setCoverUrl(fileQiniu.getDownloadPrivatePath(obj.getCoverUrl()));
            if(StringUtil.isStorage(obj.getContentUrl()))
                obj.setContentUrl(fileQiniu.getDownloadPrivatePath(obj.getContentUrl()));
        });
        return page;
    }

    private Video parse(Video obj) {
        if(StringUtil.isStorage(obj.getCoverUrl()))
            obj.setCoverUrl(fileQiniu.getDownloadPrivatePath(obj.getCoverUrl()));
        if(StringUtil.isStorage(obj.getContentUrl()))
            obj.setContentUrl(fileQiniu.getDownloadPrivatePath(obj.getContentUrl()));
        return obj;
    }
}
