package com.billion;

import com.billion.common.result.Result;
import com.billion.controller.ArticleController;
import com.billion.controller.UserController;
import com.billion.entity.Category;
import com.billion.entity.Tag;
import com.billion.entity.User;
import com.billion.mapper.article.CategoryMapper;
import com.billion.mapper.article.TagMapper;
import com.billion.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class BlogApiApplicationTests {
	@Autowired
	private UserController userController;
	@Autowired
	private ArticleController articleController;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private CategoryMapper categoryMapper;

	@Test
	void getArticleByTagId(){
		articleController.getArticleByTagId(1, 3, 2);
	}

	@Test
	void getArticleByCategory(){
		articleController.getArticleByCategory(1, 4, "数据库");
	}

	@Test
	void getCategoryInfo(){
		articleController.getCategoryInfo();
	}

	@Test
	void getTags(){
		articleService.getTagsByArticleId(1);
	}

	@Test
	void testArticle(){
		articleController.getArticleById(1);
	}

	@Test
	void testTags(){
		List<Tag> tags = tagMapper.selectList(null);
		List<Category> categories = categoryMapper.selectList(null);
		Object cates = categories.toArray();
		Object tas = tags.toArray();
		System.out.println(tas);
		System.out.println(cates);
	}
	@Test
	void contextLoads() {
		User user = new User();
		user.setUsername("王五");
		user.setPassword("wang5");
		user.setEmail("wangwu@qq.com");
		System.out.println(user);
//		Result result = userController.saveUser(user);
//		System.out.println("code = " + result.getCode());
//		System.out.println("msg = " + result.getMsg());
//		System.out.println("user = " + ((Map<String, Object>)result.getData()).get("user"));
	}

}
