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
public class Video implements Serializable{
	private static final long serialVersionUID = 1564812;
	private Integer id;//视频ID
	private String title;//视频名称
	private String coverUrl;//视频封面地址
	private String resume;//简介
	private String contentUrl;//视频地址
	private Integer likeNum;//喜欢量
	private Integer playNum;//播放量
	private Integer collectNum;//收藏量
	private User releaser;//发布者（与用户一对多关系）
	private Date releaseDate;
//	分类实现思路:上传的时候手动为媒体编写分类ID标识，随后上传操作根据媒体分类Id存放到不同的文件夹
//	private Integer sortId;//分类Id，用于媒体分区
//	private List<String> labels;//标签

}
