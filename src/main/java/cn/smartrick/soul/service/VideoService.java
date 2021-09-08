package cn.smartrick.soul.service;

import cn.smartrick.soul.entity.Video;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface VideoService {
    public abstract Page<Video> queryReleaseByUser(Integer uid, Page<Video> page);//查询某个用户发布的全部视频
    public abstract Page<Video> queryVideoBySortId(Integer sortId,Page<Video> page);//查询某个分类的全部视频
    public abstract Page<Video> queryAllVideo(Page<Video> page);//查询全部视频

    public abstract boolean addVideo(Video video);
    public abstract boolean modifyVideo(Video video);
    public abstract boolean removeVideo(Integer id);


    public abstract boolean playing(Integer id,Integer uid);//某视频被播放
    public abstract boolean liker(Integer id,Integer uid);//某视频被点赞
    public abstract boolean unliker(Integer id,Integer uid);//某视频被取消点赞
    public abstract boolean collect(Integer id,Integer uid);
    public abstract boolean uncollect(Integer id,Integer uid);
}
