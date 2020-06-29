package com.spring.base.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.Gson;
@Entity
@Table(name="Commandline")
public class CommandLine {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int quantity;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="command_id")
	private  Command command;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="article_id")
	private Article article;
	
	public CommandLine() {
	}
	
	public CommandLine(int id, int quantity, Command command, Article article) {
		this.id = id;
		this.quantity = quantity;
		this.command = command;
		this.article = article;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	@Override
	public String toString() {
		return "CommandLine [id=" + id + ", quantity=" + quantity + ", command_id=" + command.getId() + ", article_id=" + article.getId()
				+ "]";
	}

	public String toJson() {
		Gson gson= new Gson();
		return gson.toJson(this);	
	}
	

}
