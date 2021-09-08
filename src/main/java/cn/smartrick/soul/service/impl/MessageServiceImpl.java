package cn.smartrick.soul.service.impl;

import cn.smartrick.soul.constant.CloudType;
import cn.smartrick.soul.constant.MediaType;
import cn.smartrick.soul.dto.MediaSortPageDto;
import cn.smartrick.soul.entity.Cloud;
import cn.smartrick.soul.entity.Message;
import cn.smartrick.soul.mapper.CloudMapper;
import cn.smartrick.soul.mapper.MessageMapper;
import cn.smartrick.soul.mapper.MusicMapper;
import cn.smartrick.soul.mapper.SortMapper;
import cn.smartrick.soul.service.MessageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private SortMapper sortMapper;
    @Autowired
    private CloudMapper cloudMapper;

    @Override
    public Page<Message> queryReleaseByUser(Integer uid, Page<Message> page) {
        return messageMapper.selectPage(page,new QueryWrapper<Message>().eq("releaser",uid));
    }

    @Override
    public Page<Message> queryMessageBySortId(Integer sortId,Page<Message> page) {
        MediaSortPageDto mediaSortPageDto =
                new MediaSortPageDto(MediaType.MESSAGE_TYPE.getTypeCode(),
                        sortId,
                        (page.getCurrent()-1)*page.getSize(),
                        page.getSize());
        return page.setRecords(sortMapper.selectMessagesBySort(mediaSortPageDto));
    }

    @Override
    public boolean addMessage(Message message) {
        return messageMapper.insert(message) > 0;
    }

    @Override
    public boolean modifyMessage(Message message) {
        return messageMapper.updateById(message) > 0;
    }

    @Override
    public boolean removeMessage(Integer id) {
        return messageMapper.deleteById(id) > 0;
    }

    @Override
    public Page<Message> queryAllMessage(Page<Message> page) {
        return messageMapper.selectPage(page,new QueryWrapper<>());
    }

    @Override
    public boolean reading(Integer id, Integer uid) {
        if(messageMapper.reading(id)){
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.LOOK.getTypeCode());
            cloud.setMediaType(MediaType.MESSAGE_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Override
    public boolean liker(Integer id, Integer uid) {
        if(messageMapper.liker(id)){
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.LIKE.getTypeCode());
            cloud.setMediaType(MediaType.MESSAGE_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Override
    public boolean unliker(Integer id, Integer uid) {
        if(messageMapper.unliker(id)){
            return cloudMapper.delete(new QueryWrapper<Cloud>()
                    .eq("user_id",uid)
                    .eq("media_type",MediaType.MESSAGE_TYPE.getTypeCode())
                    .eq("cloud_type",CloudType.LIKE.getTypeCode())
                    .eq("media_id",id)) > 0;
        }
        return false;
    }

    @Override
    public boolean collect(Integer id, Integer uid) {
        if(messageMapper.collect(id)){
            Cloud cloud = new Cloud();
            cloud.setUserId(uid);
            cloud.setCloudType(CloudType.COLLECTION.getTypeCode());
            cloud.setMediaType(MediaType.MESSAGE_TYPE.getTypeCode());
            cloud.setMediaId(id);

            return cloudMapper.insert(cloud) > 0;
        }
        return false;
    }

    @Override
    public boolean uncollect(Integer id, Integer uid) {
        if(messageMapper.uncollect(id)){
            return cloudMapper.delete(new QueryWrapper<Cloud>()
                    .eq("user_id",uid)
                    .eq("media_type",MediaType.MESSAGE_TYPE.getTypeCode())
                    .eq("cloud_type",CloudType.COLLECTION.getTypeCode())
                    .eq("media_id",id)) > 0;
        }
        return false;
    }
}
