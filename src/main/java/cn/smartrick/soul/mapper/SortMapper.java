package cn.smartrick.soul.mapper;

import cn.smartrick.soul.dto.MediaSortPageDto;
import cn.smartrick.soul.entity.Message;
import cn.smartrick.soul.entity.Music;
import cn.smartrick.soul.entity.Sort;
import cn.smartrick.soul.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface SortMapper extends BaseMapper<Sort> {
    List<Video> selectVideosBySort(MediaSortPageDto mediaSortPageDto);
    List<Music> selectMusicsBySort(MediaSortPageDto mediaSortPageDto);
    List<Message> selectMessagesBySort(MediaSortPageDto mediaSortPageDto);
}
