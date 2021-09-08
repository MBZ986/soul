package cn.smartrick.soul.mapper;

import cn.smartrick.soul.entity.Poster;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface PosterMapper extends BaseMapper<Poster> {
    boolean reading(@Param("posterId") Integer posterId);
    boolean liker(@Param("posterId") Integer posterId);
    boolean unliker(@Param("posterId") Integer posterId);
    boolean collect(@Param("posterId") Integer posterId);
    boolean uncollect(@Param("posterId") Integer posterId);
}
