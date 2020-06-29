package com.spring.base.DAO;

import java.util.List;
import com.spring.base.Models.Category;

public interface CategoryDao {
	public void addCategory(Category c);
	public void updateCategory(Category c);
	public List<Category> listCategories();
	public Category getCategoryById(int id);
	public void removeCategory(int id);

}
