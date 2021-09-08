package cn.smartrick.soul.constant;

public enum CloudType {
	//媒体类型ID：视频01，音乐02，帖子03，资讯04，壁纸05，评论06;
	LIKE(1,"喜欢"),
	COLLECTION(2,"收藏"),
	RELEASE(3,"发布"),
	PLAYING(4,"播放历史"),
	LOOK(5,"浏览历史");


	private Integer typeCode = null;
	private String desc = null;


	CloudType(Integer typeCode, String desc){
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
