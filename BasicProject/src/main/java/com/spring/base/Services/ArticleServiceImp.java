package com.spring.base.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.base.DAO.ArticleDao;
import com.spring.base.Models.Article;
@Service
public class ArticleServiceImp implements ArticleService{
	private ArticleDao articleDao;
	

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	@Override
	@Transactional
	public void addArticle(Article a,int category_id) {
		this.articleDao.addArticle(a,category_id);
	}

	@Override
	@Transactional
	public void updateArticle(Article a,int category_id) {
		this.articleDao.updateArticle(a,category_id);
	}

	@Override
	@Transactional
	public List<Article> listArticles() {
		return this.articleDao.listArticles();
	}

	@Override
	@Transactional
	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}

	@Override
	@Transactional
	public void removeArticle(int id) {
		this.articleDao.removeArticle(id);
	}

	@Override
	@Transactional
	public List<Article> articlesByCategory(int category_id) {
		
		return this.articleDao.articlesByCategory(category_id);
	};
	

}
