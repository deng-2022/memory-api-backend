package memory.cloud.memoryclient.model.dto.picture;

import lombok.Data;

/**
 * @author 邓哈哈
 * 2023/11/19 22:44
 * Function:
 * Version 1.0
 */
@Data
public class PictureQueryRequest {
    private String searchText;
    private int pageSize;
    private int currentPage;
}
