package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName words
 */
@TableName(value ="words")
@Data
public class Words implements Serializable {
    private Long id;

    private String name;

    private String content;

    private String tags;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}