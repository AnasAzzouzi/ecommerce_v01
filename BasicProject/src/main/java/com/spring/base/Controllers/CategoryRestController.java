package com.spring.base.Controllers;

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
import com.spring.base.Models.Category;
import com.spring.base.Services.CategoryService;
import java.util.List;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins="*")
@Transactional
@RequestMapping("/RestCategories")
public class CategoryRestController {

	private CategoryService categoryService;
	@Autowired(required=true)
	@Qualifier(value="categoryService")
	public void setCategorySerice(CategoryService cs) {
		this.categoryService=cs;
	}
	
	@RequestMapping(value="/getCategories",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getCategories(){
		
		List<Category>  categories=this.categoryService.listCategories();
		String categoriesJson = new Gson().toJson(categories);
		return new ResponseEntity<String>(categoriesJson,HttpStatus.OK);
		
	}
	@RequestMapping(value="/addCategory",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addCategory(@RequestBody String data) {
		this.categoryService.addCategory(Category.CreateCategoryFromJson(data));
	}
	@RequestMapping(value="/updateCategory",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateCategory(@RequestBody String data) {
		
		Category cat= Category.CreateCategoryFromJson(data);
		this.categoryService.updateCategory(cat);
	}
	
	@RequestMapping(value="/deleteCategory",method=RequestMethod.DELETE)
	public void deleteCategory(@RequestParam(name="id")String id) {
		this.categoryService.removeCategory(Integer.parseInt(id));

	}
	
	
}
