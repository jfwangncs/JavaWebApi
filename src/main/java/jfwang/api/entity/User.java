package jfwang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType; 
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data; 

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableName("Users")
public class User implements Serializable {
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;
    private String uid;
    private String userName; 
    private String regdate;
    private String avatar;
    private Integer state;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
