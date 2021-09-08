package cn.smartrick.soul.service;

import cn.smartrick.soul.entity.Poster;
import cn.smartrick.soul.entity.Video;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface PosterService {
    public abstract Page<Poster> queryReleaseByUser(Integer uid, Page<Poster> page);//查询某个用户的全部帖子
    public abstract Page<Poster> queryAllPoster(Page<Poster> page);//查询全部帖子
    public abstract boolean addPoster(Poster poster);
    public abstract boolean removePoster(Integer id);
    public abstract boolean modifyPoster(Poster poster);
    public abstract boolean reading(Integer id,Integer uid);//某帖子被阅读
    public abstract boolean liker(Integer id,Integer uid);//帖子被点赞
    public abstract boolean unliker(Integer id,Integer uid);//帖子被取消点赞
    public abstract boolean collect(Integer id,Integer uid);
    public abstract boolean uncollect(Integer id,Integer uid);
}
