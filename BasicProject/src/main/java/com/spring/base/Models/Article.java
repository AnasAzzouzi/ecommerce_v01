package com.spring.base.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
@Entity
@Table(name="Article")
public class Article {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	private int stock;
	private String photo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category category;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="article")
	private transient List<CommandLine> commandLines;
	
	
	
	public Article() {
	}
	
	public Article(int id, String name, double price, int stock, String photo, Category category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.photo = photo;
		this.category = category;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", photo=" + photo
				+ ", category=" + category + "]";
	}

	public static Article CreateArticleFromJson(String articleJson) {
		
		Article article	= new Article();
		JsonObject jsonObject = new JsonParser().parse(articleJson).getAsJsonObject();
		try {
			article.setId(jsonObject.get("id_article").getAsInt());
		}catch(Exception e) {}
		try {
			article.setName(jsonObject.get("article_name").getAsString());
		}catch(Exception e) {}
		try {
			article.setPrice(jsonObject.get("price").getAsDouble());
		}catch(Exception e) {}
		try {
			article.setStock(jsonObject.get("stock").getAsInt());
		}catch(Exception e) {}
		try {
			article.setPhoto(jsonObject.get("photo").getAsString());
		}catch(Exception e) {}
		
		return article;
	}
	public String toJson() {
		Gson gson= new Gson();
		return gson.toJson(this);
		
		
	}
	
	 
	
}
