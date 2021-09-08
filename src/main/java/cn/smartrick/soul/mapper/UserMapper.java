package cn.smartrick.soul.mapper;

import cn.smartrick.soul.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectFollows(@Param("userId") Integer userId,@Param("current") Long current,@Param("size")Long size);
    List<User> selectFans(@Param("userId") Integer userId,@Param("current") Long current,@Param("size")Long size);
    boolean insertFollow(@Param("userId") Integer userId,@Param("followId")Integer followId);
    boolean deleteFollow(@Param("userId") Integer userId,@Param("followId")Integer followId);
    List<User> selectAllUserSimple(@Param("current") Long current,@Param("size")Long size);
}
