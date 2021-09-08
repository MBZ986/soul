package cn.smartrick.soul.service;

import cn.smartrick.soul.entity.Message;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.File;
import java.util.List;

public interface MessageService {
    public abstract Page<Message> queryReleaseByUser(Integer uid, Page<Message> page);//查询某个用户发布的全部资讯

    public abstract Page<Message> queryAllMessage(Page<Message> page);//查询某个用户发布的全部资讯

    public abstract Page<Message> queryMessageBySortId(Integer sortId, Page<Message> page);

    public abstract boolean addMessage(Message message);//发布文字版新闻

    public abstract boolean modifyMessage(Message message);

    public abstract boolean removeMessage(Integer id);

    public abstract boolean reading(Integer id, Integer uid);//某新闻被阅读

    public abstract boolean liker(Integer id, Integer uid);//某新闻被点赞

    public abstract boolean unliker(Integer id, Integer uid);//某新闻被取消点赞

    public abstract boolean collect(Integer id, Integer uid);

    public abstract boolean uncollect(Integer id, Integer uid);
}
