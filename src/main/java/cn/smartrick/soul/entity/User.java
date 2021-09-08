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
public class User implements Serializable{
	private static final long serialVersionUID = 1975217;
	private Integer id;
	private String username;//用户名
	private String password;//用户密码
	private String headImgUrl;//用户头像
	private String topImgUrl;//用户页面顶部图片
	private String email;//电子邮箱
	private String signature;//个性签名
	private Date birthday;//生日
	private int root;//是否是管理员(默认为false)


}
