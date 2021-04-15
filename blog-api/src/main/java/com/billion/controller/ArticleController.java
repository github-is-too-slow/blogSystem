package com.billion.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.billion.common.annotation.LogAnnotation;
import com.billion.common.result.Result;
import com.billion.common.result.ResultCode;
import com.billion.entity.Article;
import com.billion.entity.Category;
import com.billion.entity.Tag;
import com.billion.entity.User;
import com.billion.service.ArticleService;
import com.billion.service.UserService;
import com.billion.utils.PathUtils;
import com.billion.vo.ArticleVo;
import com.billion.vo.AuthorVo;
import com.billion.vo.PageVo;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.ResultType;
import org.apache.naming.factory.ResourceLinkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Billion
 * @create 2021/04/07 16:17
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @GetMapping("/getArticleByTagId")
    @LogAnnotation(module = "文章模块", operation = "根据标签id获取分页数据")
    public Result getArticleByTagId(@RequestParam("pageNumber") Integer pageNumber,
                                    @RequestParam("pageSize") Integer pageSize,
                                    @RequestParam("tagId") Integer tagId){
        Page<Article> page1 = new Page<>(pageNumber, pageSize);
        Page<Article> page2 = articleService.getArticleByTagId(page1, tagId);
        return capsulatePageVo(page2);
    }

    @GetMapping("/getArticleByCategory")
    @LogAnnotation(module = "文章模块", operation = "根据分类名获取分页数据")
    public Result getArticleByCategory(@RequestParam("pageNumber") Integer pageNumber,
                                       @RequestParam("pageSize") Integer pageSize,
                                       @RequestParam("categoryName") String categoryName){
        Page<Article> page1 = new Page<>(pageNumber, pageSize);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", categoryName);
        Page<Article> page2 = articleService.page(page1, queryWrapper);
        return capsulatePageVo(page2);
    }

    @GetMapping("/list")
    @LogAnnotation(module = "文章模块", operation = "获取所有文章的分页数据")
    public Result getArticles(@RequestParam("pageNumber") Integer pageNumber,
                              @RequestParam("pageSize") Integer pageSize){
        Page<Article> page1 = new Page<>(pageNumber, pageSize);
        Page<Article> page2 = articleService.page(page1);
        return capsulatePageVo(page2);
    }

    @GetMapping("/getArticleById")
    @LogAnnotation(module = "文章模块", operation = "根据id获取文章详情")
    public Result getArticleById(@RequestParam("id") Integer id){
        //首先更新文章阅读量
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        String updateSql = "view_counts = view_counts + 1";
        updateWrapper.setSql(updateSql).eq("id", id);
        articleService.update(updateWrapper);
        //获取文章详情信息
        Article article = articleService.getById(id);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "nickname", "adavar").eq("id", article.getAuthor());
        User author = userService.getOne(queryWrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("article", article);
        data.put("author", author);
        Result result = Result.success();
        result.setData(data);
        return result;
    }

    @PostMapping("/upload")
    @LogAnnotation(module = "文章模块", operation = "上传文章中的图片")
    public Result upload(HttpServletRequest request,
                         @RequestPart("img") MultipartFile img){
        String originalFilename = img.getOriginalFilename();
        String saveFilename = new Date().getTime() + originalFilename;
        File uploadDest = PathUtils.getUploadDest("static/api/image/blog/", saveFilename);
        try {
            img.transferTo(uploadDest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgUrl = PathUtils.getUploadFileUrl(request.getScheme(), "localhost",
                "/api/image/blog/", saveFilename);
        Result result = Result.success();
        result.simple().put("imgUrl", imgUrl);
        return result;
    }

    @PostMapping("/publish")
    @LogAnnotation(module = "文章模块", operation = "发布文章")
    public Result publish(@RequestBody Article article){
        article.setCreateDate(new Date());
        articleService.save(article);
        articleService.bindArticleAndTags(article.getId(), article.getTags());
        Result result = Result.success();
        result.simple().put("id", article.getId());
        return result;
    }

    @GetMapping("/getMetaInfo")
    @LogAnnotation(module = "文章模块", operation = "获取所有文章分类和标签类型")
    public Result getCategorysAndTags(){
        Map<String, Object[]> categorysAndTags = articleService.getCategorysAndTags();
        if(null == categorysAndTags){
            return Result.error(ResultCode.ERROR);
        }
        Result result = Result.success(categorysAndTags);
        return result;
    }

    @GetMapping("/getCategoryInfo")
    @LogAnnotation(module = "文章模块", operation = "获取所有分类信息")
    public Result getCategoryInfo(){
        List<Category> categorys = articleService.getCategoryInfo();
        if(null == categorys){
            return Result.error(ResultCode.ERROR);
        }
        Result result = Result.success();
        result.simple().put("categorys", categorys);
        return result;
    }

    @GetMapping("/getTagInfo")
    @LogAnnotation(module = "文章模块", operation = "获取所有标签信息")
    public Result getTagInfo(){
        List<Tag> tags = articleService.getTagInfo();
        if(null == tags){
            return Result.error(ResultCode.ERROR);
        }
        Result result = Result.success();
        result.simple().put("tags", tags);
        return result;
    }

    @LogAnnotation(module = "文章模块", operation = "封装分页数据")
    private Result capsulatePageVo(Page<Article> articlePage){
        PageVo page = new PageVo();
        page.setCurrent(articlePage.getCurrent());
        page.setPages(articlePage.getPages());
        page.setTotal(articlePage.getTotal());
        List<ArticleVo> records = new ArrayList<>();
        for(Article article: articlePage.getRecords()){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.select("nickname").eq("id", article.getAuthor());
            User author = userService.getOne(wrapper);
            ArticleVo articleVo = new ArticleVo();
            articleVo.setId(article.getId());
            articleVo.setTitle(article.getTitle());
            articleVo.setCommentCounts(article.getCommentCounts());
            articleVo.setViewCounts(article.getViewCounts());
            articleVo.setSummary(article.getSummary());
            AuthorVo authorVo = new AuthorVo();
            authorVo.setNickname(author.getNickname());
            articleVo.setAuthor(authorVo);
            articleVo.setCreateDate(article.getCreateDate());
            articleVo.setTags(articleService.getTagsByArticleId(article.getId()));
            records.add(articleVo);
        }
        if(records.size() == 0){
            return Result.error(ResultCode.ERROR);
        }
        page.setRecords(records);
        Result result = Result.success();
        result.simple().put("page", page);
        return result;
    }
}
