package cn.smartrick.soul.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaSortPageDto {
    private Integer mediaType;
    private Integer sortId;
    private Long current;
    private Long size;
}
