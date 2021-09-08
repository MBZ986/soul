package cn.smartrick.soul.service.impl;

import cn.smartrick.soul.constant.CloudType;
import cn.smartrick.soul.constant.MediaType;
import cn.smartrick.soul.dto.MediaSortPageDto;
import cn.smartrick.soul.entity.Cloud;
import cn.smartrick.soul.entity.Music;
import cn.smartrick.soul.entity.User;
import cn.smartrick.soul.mapper.CloudMapper;
import cn.smartrick.soul.mapper.MusicMapper;
import cn.smartrick.soul.mapper.SortMapper;
import cn.smartrick.soul.service.MusicService;
import cn.smartrick.soul.util.StringUtil;
import cn.smartrick.soul.util.UploadFileQiniu;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;
    @Autowired
    private SortMapper sortMapper;
    @Autowired
    private CloudMapper cloudMapper;
    @Autowired
    private UploadFileQiniu fileQiniu;

    @Override
    public Page<Music> queryMusicByAuthor(String author,Page<Music> page) {
        return parse(musicMapper.selectPage(page,new QueryWrapper<Music>().eq("author", author)));
    }

    @Override
    public Page<Music> queryMusicBySortId(Integer sortId, Page<Music> page) {
        MediaSortPageDto mediaSortPageDto =
                new MediaSortPageDto(MediaType.MUSIC_TYPE.getTypeCode(),
                        sortId,
                        (page.getCurrent()-1)*page.getSize(),
                        page.getSize());
        return parse(page.setRecords(sortMapper.selectMusicsBySort(mediaSortPageDto)));
    }

    @Override
    public Page<Music> queryAllMusic(Page<Music> page) {
        return parse(musicMapper.selectPage(page,new QueryWrapper<>()));
    }

    @Override
    public boolean addMusic(Music music) {
        return musicMapper.insert(music) > 0;
    }

    @Override
    public boolean modifyMusic(Music music) {
        return musicMapper.updateById(music) > 0;
    }

    @Override
    public boolean removeMusic(Integer id) {
        return musicMapper.deleteById(id) > 0;
    }

    @Override
    public boolean playing(Integer id, Integer uid) {
        if (musicMapper.playing(id)) {
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.PLAYING.getTypeCode());
            cloud.setMediaType(MediaType.MUSIC_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Override
    public boolean liker(Integer id, Integer uid) {
        if (musicMapper.liker(id)) {
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.LIKE.getTypeCode());
            cloud.setMediaType(MediaType.MUSIC_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Override
    public boolean unliker(Integer id, Integer uid) {
        if (musicMapper.unliker(id)) {
            return cloudMapper.delete(new QueryWrapper<Cloud>()
                    .eq("user_id", uid)
                    .eq("media_type", MediaType.MUSIC_TYPE.getTypeCode())
                    .eq("cloud_type",CloudType.LIKE.getTypeCode())
                    .eq("media_id", id)) > 0;
        }
        return false;
    }

    @Override
    public boolean collect(Integer id, Integer uid) {
        if(musicMapper.collect(id)){
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.COLLECTION.getTypeCode());
            cloud.setMediaType(MediaType.MUSIC_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Override
    public boolean uncollect(Integer id, Integer uid) {
        if(musicMapper.uncollect(id)){
            return cloudMapper.delete(new QueryWrapper<Cloud>()
                    .eq("user_id",uid)
                    .eq("media_type",MediaType.MUSIC_TYPE.getTypeCode())
                    .eq("cloud_type",CloudType.COLLECTION.getTypeCode())
                    .eq("media_id",id)) > 0;
        }
        return false;
    }

    private Page<Music> parse(Page page) {
        ((List<Music>) page.getRecords()).forEach(obj -> {
            if(StringUtil.isStorage(obj.getCoverUrl()))
                obj.setCoverUrl(fileQiniu.getDownloadPrivatePath(obj.getCoverUrl()));
            if(StringUtil.isStorage(obj.getContentUrl()))
                obj.setContentUrl(fileQiniu.getDownloadPrivatePath(obj.getContentUrl()));
        });
        return page;
    }

    private Music parse(Music obj) {
        if(StringUtil.isStorage(obj.getCoverUrl()))
            obj.setCoverUrl(fileQiniu.getDownloadPrivatePath(obj.getCoverUrl()));
        if(StringUtil.isStorage(obj.getContentUrl()))
            obj.setContentUrl(fileQiniu.getDownloadPrivatePath(obj.getContentUrl()));
        return obj;
    }
}
