package com.billion.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.billion.entity.Article;
import com.billion.entity.ArticleTag;
import com.billion.entity.Category;
import com.billion.entity.Tag;
import com.billion.mapper.article.ArticleMapper;
import com.billion.mapper.article.ArticleTagMapper;
import com.billion.mapper.article.CategoryMapper;
import com.billion.mapper.article.TagMapper;
import com.billion.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Billion
 * @create 2021/04/07 17:19
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public Map<String, Object[]> getCategorysAndTags() {
        List<Category> categorys = categoryMapper.selectList(null);
        List<Tag> tags = tagMapper.selectList(null);
        if(null == categorys || null == tags){
            return null;
        }
        Map<String, Object[]> categorysAndTags = new HashMap<>();
        String[] categoryNames = new String[categorys.size()];
        for (int i = 0; i < categorys.size(); i++) {
            categoryNames[i] = categorys.get(i).getName();
        }
        String[] tagNames = new String[tags.size()];
        for (int i = 0; i < tags.size(); i++) {
            tagNames[i] = tags.get(i).getName();
        }
        categorysAndTags.put("categorys", categoryNames);
        categorysAndTags.put("tags", tagNames);
        return categorysAndTags;
    }

    @Override
    public List<Category> getCategoryInfo() {
        return categoryMapper.selectCategoryInfo(null);
    }

    @Override
    public List<Tag> getTagInfo() {
        return tagMapper.selectList(null);
    }

    @Override
    public void bindArticleAndTags(Integer articleId, String[] tags) {
        for(String tag: tags){
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("name", tag);
            Tag dbTag = tagMapper.selectOne(wrapper);
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticle(articleId);
            articleTag.setTag(dbTag.getId());
            articleTagMapper.insert(articleTag);
        }
    }

    @Override
    public List<String> getTagsByArticleId(Integer id) {
        QueryWrapper<ArticleTag> wrapper = new QueryWrapper<>();
        wrapper.select("tag").eq("article", id);
        List<ArticleTag> articleTags = articleTagMapper.selectList(wrapper);
        List<String> tags = new ArrayList<>();
        for(ArticleTag articleTag: articleTags){
            Tag tag = tagMapper.selectById(articleTag.getTag());
            tags.add(tag.getName());
        }
        return tags;
    }

    @Override
    public Page<Article> getArticleByTagId(Page<Article> page, Integer tagId) {
        return articleMapper.selectArticleByTagId(page, tagId);
    }
}
