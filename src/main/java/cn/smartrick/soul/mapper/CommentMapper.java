package cn.smartrick.soul.mapper;

import cn.smartrick.soul.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {

    Page<Comment> selectCommentsByMedia(@Param("mediaType") Integer mtype, @Param("mediaId")  Integer mid, @Param("current") Long current, @Param("size")Long size);
    boolean liker(@Param("commentId") Integer commentId);
    boolean unliker(@Param("commentId") Integer commentId);
}
