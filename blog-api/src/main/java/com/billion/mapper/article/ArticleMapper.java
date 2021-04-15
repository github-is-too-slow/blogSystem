package com.billion.mapper.article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.billion.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Billion
 * @create 2021/04/07 16:31
 */
public interface ArticleMapper extends BaseMapper<Article>{
    String childrenQuerySql = "select article from article_tag " +
                            "where tag = #{tagId}";
    String querySql = "select * from article where id in (" +
            childrenQuerySql + ")";
    @Select(querySql)
    Page<Article> selectArticleByTagId(Page<Article> page, @Param("tagId") Integer tagId);
}
