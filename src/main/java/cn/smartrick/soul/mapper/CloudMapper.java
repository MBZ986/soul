package cn.smartrick.soul.mapper;

import cn.smartrick.soul.entity.Cloud;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CloudMapper extends BaseMapper<Cloud> {
    boolean clearCloudByUser(@Param("uid") Integer uid);
}
