package cn.smartrick.soul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Sort {
    private Integer id;
    private Integer sortId;
    private String sortName;
    private String desc;
}
