package memory.cloud.memoryclient.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import memory.cloud.memoryclient.model.domain.Picture;
import memory.cloud.memoryclient.service.PictureService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author 邓哈哈
 * 2023/8/30 17:23
 * Function:
 * Version 1.0
 */
@Service
public class PictureServiceImpl implements PictureService {

    /**
     * 爬取图片
     *
     * @param category 类型
     */
    @Override
    public Page<Picture> listPictureVOByPage(String category) throws IOException {
        // 非空条件，转码
        if (StringUtils.isNotBlank(category)) {
            category = URLEncoder.encode(category, "UTF-8");
        }

        Random random = new Random();
        int randomPage = random.nextInt(5) + 1;
        String url = String.format("https://www.vcg.com/creative-image/%s/?page=%d", category, randomPage);
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 Edg/116.0.1938.81";

        Connection connect = Jsoup.connect(url)
                .header("User-Agent", userAgent); // 设置User-Agent头信息
        Document doc = connect.get();
        Elements galleryItem = Objects.requireNonNull(doc.getElementById("imageContent"))
                .select(".gallery_inner")
                .select(".galleryItem");

        // 随机选择五个标签
        List<Element> selectedItems = galleryItem.subList(0, 5);

        List<Picture> pictureList = new ArrayList<>();
        // 打印选中的标签
        for (Element item : selectedItems) {
            Elements img = item.select("> a > img");
            String src = "https:" + img.attr("data-src");
            String title = img.attr("title");

            Picture picture = new Picture();
            picture.setCategory(category);
            picture.setTitle(title);
            picture.setUrl(src);

            pictureList.add(picture);
        }

        Page<Picture> picturePage = new Page<>();

        picturePage.setRecords(pictureList);
        return picturePage;
    }

    private String getImageUrl(Element element) {
        String m = element.select(".iusc").get(0).attr("m");
        Map<String, Object> map = JSONUtil.toBean(m, Map.class);
        return (String) map.get("murl");
    }

    private String getTitle(Element element) {
        return element.select(".inflnk").get(0).attr("aria-label");
    }
}
