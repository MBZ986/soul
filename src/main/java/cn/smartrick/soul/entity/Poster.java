package cn.smartrick.soul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Clob;

//帖子类
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Poster implements Serializable{
	private static final long serialVersionUID = 4684123;
	private Integer id;
	private Clob content;//帖子内容
	private String imgs;//帖子图片
	private User releaser;//发布者
	private Integer reading;//阅读量
	private Integer likeNum;//喜欢量

	
}
