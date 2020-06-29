package com.spring.base.DAO;

import java.util.List;

import com.spring.base.Models.Article;

public interface ArticleDao {
	
	public void addArticle(Article a,int category_id);
	public void updateArticle(Article a,int category_id);
	public List<Article> listArticles();
	public Article getArticleById(int id);
	public void removeArticle(int id);
	public List<Article> articlesByCategory(int category_id);
	
}
