package cn.smartrick.soul.mapper;

import cn.smartrick.soul.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper extends BaseMapper<Message> {
    boolean reading(@Param("messageId") Integer messageId);
    boolean liker(@Param("messageId") Integer messageId);
    boolean unliker(@Param("messageId") Integer messageId);
    boolean collect(@Param("messageId") Integer messageId);
    boolean uncollect(@Param("messageId") Integer messageId);
}
