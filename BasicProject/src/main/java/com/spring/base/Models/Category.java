package com.spring.base.Models;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;
import javax.persistence.CascadeType;

@Entity
@Table(name="Category")
public class Category {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="category",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private transient Collection<Article> articles;
	
	public Category() {}

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Article> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}

	public static Category CreateCategoryFromJson(String categoryJson) {
		Category category=new Category();
		JsonObject jsonObject = new JsonParser().parse(categoryJson).getAsJsonObject();
		try {
			category.setId(jsonObject.get("id_category").getAsInt());
			}catch(Exception e) {}
		
		try {
			category.setName(jsonObject.get("category_name").getAsString());
			}catch(Exception e) {}
		
				return category;	
				
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

	public String toJson() {
		Gson gson=new Gson();
		return gson.toJson(this);
	}

	
	
	

}
