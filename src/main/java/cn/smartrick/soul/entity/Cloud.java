package cn.smartrick.soul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Clob;


//用户云数据
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Cloud implements Serializable{
	private static final long serialVersionUID = 1354645;
	private Integer id;
	private Integer userId;//用户Id
	private Integer mediaId;//媒体ID
	private Integer mediaType;//媒体类型
	private Integer cloudType;//缓存类型
	private String cacheInfo;//缓存详细信息

}
