package com.spring.base.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.base.DAO.CategoryDao;
import com.spring.base.Models.Category;
@Service
public class CategoryServiceImp implements CategoryService{
	
	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	@Transactional
	public void addCategory(Category c) {
		this.categoryDao.addCategory(c);		
	}

	@Override
	@Transactional
	public void updateCategory(Category c) {
		this.categoryDao.updateCategory(c);
	}

	@Override
	@Transactional
	public List<Category> listCategories() {
		return this.categoryDao.listCategories();
	}

	@Override
	@Transactional
	public Category getCategoryById(int id) {
		return this.categoryDao.getCategoryById(id);
	}

	@Override
	@Transactional
	public void removeCategory(int id) {
		this.categoryDao.removeCategory(id);
	}
	

}
