package com.billion.mapper.article;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.billion.entity.Article;
import com.billion.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Billion
 * @create 2021/04/07 16:41
 */
public interface CategoryMapper extends BaseMapper<Category>{
    String childQuerySql = "select count(*) counts, category " +
                            "from article " +
                            "group by category";
    String querySql = "select c.*, b.counts " +
                      "from category c " +
                      "left JOIN (" + childQuerySql + ") b " +
                      "on c.name = b.category";

    @Select(querySql)
    List<Category> selectCategoryInfo(@Param("ew") Wrapper<Article> queryWrapper);
}
