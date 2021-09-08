package cn.smartrick.soul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Clob;

//资讯类
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Message implements Serializable{
	private static final long serialVersionUID = 1392546;
	private Integer id;
	private String title;//资讯标题
	private String cover;//资讯封面
	private Clob content;//资讯内容
	private User releaser;//发布者(一对一：一条新闻对应一个发布者)
	private Integer readNum;//阅读量
	private Integer likeNum;//喜欢量

	
}
