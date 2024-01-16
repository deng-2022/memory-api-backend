package memory.cloud.memoryclient.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName words
 */
@TableName(value = "words")
@Data
public class Words implements Serializable {
    private Long id;

    private String name;

    private String content;

    private Integer type;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}