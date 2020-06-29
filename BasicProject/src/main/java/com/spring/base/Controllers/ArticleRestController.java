package com.spring.base.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.base.Models.Article;
import com.spring.base.Services.ArticleService;

@RestController
@CrossOrigin(origins="*")
@Transactional
@RequestMapping("/RestArticles")
public class ArticleRestController {
	private ArticleService articleService;
	@Autowired(required=true)
	@Qualifier(value="articleService")
	public void setArticleService(ArticleService as) {
		this.articleService=as;
	}
	
	@RequestMapping(value="/getArticles",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getArticles(){
		List<Article>  articles=this.articleService.listArticles();
		String articlesJson = new Gson().toJson(articles);
		return new ResponseEntity<String>(articlesJson,HttpStatus.OK);
		
	}
	@RequestMapping(value="/addArticle",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addArticle(@RequestBody String data) {
		
		JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
		int category_id=jsonObject.get("category_id").getAsInt();
		this.articleService.addArticle(Article.CreateArticleFromJson(data),category_id);
	}
	@RequestMapping(value="/updateArticle",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateArticle(@RequestBody String data) {
		
		JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
		int category_id=jsonObject.get("category_id").getAsInt();
		this.articleService.updateArticle(Article.CreateArticleFromJson(data),category_id);
	}
	@RequestMapping(value="/articlesByCategory",method=RequestMethod.GET)
	public ResponseEntity<String> articlesByCategory(@RequestParam(name="id") String id) {
		List<Article>  articles=this.articleService.articlesByCategory(Integer.parseInt(id));
		String articlesJson = new Gson().toJson(articles);
		return new ResponseEntity<String>(articlesJson,HttpStatus.OK);
		
	}
	

}
