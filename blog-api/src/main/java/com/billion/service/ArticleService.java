package com.billion.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.billion.entity.Article;
import com.billion.entity.Category;
import com.billion.entity.Tag;

import java.util.List;
import java.util.Map;

/**
 * @author Billion
 * @create 2021/04/07 17:14
 */
public interface ArticleService extends IService<Article>{
    Map<String, Object[]> getCategorysAndTags();
    List<Category> getCategoryInfo();
    List<Tag> getTagInfo();
    void bindArticleAndTags(Integer articleId, String[] tags);
    List<String> getTagsByArticleId(Integer id);
    Page<Article> getArticleByTagId(Page<Article> page, Integer tagId);
}
