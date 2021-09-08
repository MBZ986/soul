package cn.smartrick.soul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Clob;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Comment implements Serializable{
	private static final long serialVersionUID = 4467813;
	private Integer id;
	private Integer mediaId;//多对一：多条评论对一应一个媒体
	private Integer mediaType;//多对一：多条评论对一应一个媒体
	private User releaser;//一对一：一条评论对一条评论
	private String content;
	private Integer likeNum;

}
