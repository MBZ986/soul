package cn.smartrick.soul.mapper;

import cn.smartrick.soul.entity.Music;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface MusicMapper extends BaseMapper<Music> {
    boolean playing(@Param("musicId") Integer musicId);
    boolean liker(@Param("musicId") Integer musicId);
    boolean unliker(@Param("musicId") Integer musicId);
    boolean collect(@Param("musicId") Integer musicId);
    boolean uncollect(@Param("musicId") Integer musicId);
}
