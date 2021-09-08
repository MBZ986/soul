package cn.smartrick.soul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Music implements Serializable {
    private static final long serialVersionUID = 194352;
    private Integer id;//歌曲ID
    private String name;//歌曲名称
    private String author;//歌手作者
    private String coverUrl;//歌曲封面地址
    private String contentUrl;//歌曲地址
    private Integer playNum;//播放量
    private Integer likeNum;//喜欢量
    private Integer collectNum;//收藏量
    private Date releaseDate;//发布日期

}
