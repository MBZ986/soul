package cn.smartrick.soul.mapper;

import cn.smartrick.soul.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper extends BaseMapper<Video> {
    List<Video> selectPageVideo(@Param("current") Long current,@Param("size") Long size);
    List<Video> selectVideoById(@Param("id")Integer id);
    boolean updateVideoById(@Param("video")Video video);
    boolean playing(@Param("videoId") Integer videoId);
    boolean liker(@Param("videoId") Integer videoId);
    boolean unliker(@Param("videoId") Integer videoId);
    boolean collect(@Param("videoId") Integer videoId);
    boolean uncollect(@Param("videoId") Integer videoId);
}
