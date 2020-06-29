package com.spring.base.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spring.base.Models.Article;
import com.spring.base.Models.Category;

public class ArticleDaoImp implements ArticleDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addArticle(Article a,int category_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Category c = (Category) session.load(Category.class, category_id);
		a.setCategory(c);
		session.persist(a);
	}

	@Override
	public void updateArticle(Article a,int category_id) {
		Session session = this.sessionFactory.getCurrentSession();	
		Category c = (Category) session.load(Category.class, category_id);
		a.setCategory(c);
		session.update(a);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> listArticles() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Article> articlesList=session.createQuery("from Article").list();
		return articlesList;
	}

	@Override
	public Article getArticleById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Article a =(Article) session.load(Article.class, id);
		return a;
	}

	@Override
	public void removeArticle(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Article a =(Article) session.load(Article.class, id);
		if(null != a){
			session.delete(a);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> articlesByCategory(int category_id) {
		Session session = this.sessionFactory.getCurrentSession();
		String query="from Article where category_id="+category_id;
		List<Article> articlesList=session.createQuery(query).list();
		return articlesList;
		
	}

}
