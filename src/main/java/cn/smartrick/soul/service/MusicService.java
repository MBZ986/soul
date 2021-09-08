package cn.smartrick.soul.service;

import cn.smartrick.soul.entity.Music;
import cn.smartrick.soul.entity.Video;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface MusicService {
    public abstract Page<Music> queryMusicByAuthor(String author, Page<Music> page);//查询某个歌手的所有歌曲

    public abstract Page<Music> queryMusicBySortId(Integer sortId, Page<Music> page);//查询某个分类的所有歌曲

    public abstract Page<Music> queryAllMusic(Page<Music> page);//查询所有歌曲

    public abstract boolean addMusic(Music music);

    public abstract boolean modifyMusic(Music music);

    public abstract boolean removeMusic(Integer id);

    public abstract boolean playing(Integer id, Integer uid);//歌曲被播放

    public abstract boolean liker(Integer id, Integer uid);//某歌曲被点赞

    public abstract boolean unliker(Integer id, Integer uid);//某歌曲被取消点赞

    public abstract boolean collect(Integer id, Integer uid);

    public abstract boolean uncollect(Integer id, Integer uid);
}
