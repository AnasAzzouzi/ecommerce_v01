package com.spring.base.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import com.spring.base.Models.Category;

public class CategoryDaoImp implements CategoryDao{
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	@Override
	public void addCategory(Category c) {
		Session session=this.sessionFactory.getCurrentSession();
		session.persist(c);
	}

	@Override
	public void updateCategory(Category c) {
		Session session=this.sessionFactory.getCurrentSession();
		session.update(c);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Category> listCategories() {
		Session session=this.sessionFactory.getCurrentSession();
		List<Category> categoriesList = session.createQuery("from Category").list();
		return categoriesList;
		
	}

	@Override
	public Category getCategoryById(int id) {
		Session session=this.sessionFactory.getCurrentSession();
		Category c = (Category) session.load(Category.class, id);
		return c;
	}

	@Override
	public void removeCategory(int id) {
		Session session=this.sessionFactory.getCurrentSession();
		Category c=(Category) session.load(Category.class, id);
		if(null !=c) {
			session.delete(c);
		}
		
	}
	

}
