package cn.smartrick.soul.constant;

public enum MediaType {
	//媒体类型ID：视频01，音乐02，帖子03，资讯04，壁纸05，评论06;
	VIDEO_TYPE(10,"视频"),
	MUSIC_TYPE(11,"音乐"),
	POSTER_TYPE(12,"帖子"),
	MESSAGE_TYPE(13,"资讯"),
	WALLPAPER_TYPE(14,"壁纸"),
	COMMENT_TYPE(15,"评论");

	private Integer typeCode = null;
	private String desc = null;


	MediaType(Integer typeCode,String desc){
		this.typeCode = typeCode;
		this.desc = desc;
	}

	public Integer getTypeCode(){
		return this.typeCode;
	}

	public String getDesc(){
		return this.desc;
	}
}
