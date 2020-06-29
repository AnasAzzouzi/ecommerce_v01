package com.spring.base.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;
@Entity
@Table(name="Command")
public class Command {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int client_id;
	private Date command_date;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="command",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private transient List<CommandLine> commandLines;
	


	public Command(int id, int client_id, Date command_date, List<CommandLine> commandLines) {
		this.id = id;
		this.client_id = client_id;
		this.command_date = command_date;
		this.commandLines = commandLines;
	}
	
	public Command() {}
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getClient_id() {
		return client_id;
	}


	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}


	public Date getCommand_date() {
		return command_date;
	}


	public void setCommand_date(Date command_date) {
		this.command_date = command_date;
	}


	public List<CommandLine> getCommandLines() {
		return commandLines;
	}


	public void setCommandLines(List<CommandLine> commandLines) {
		this.commandLines = commandLines;
	}


	@Override
	public String toString() {
		return "Command [id=" + id + ", client_id=" + client_id + ", command_date=" + command_date + "]";
	}
	
	public String toJson() {
		Gson gson= new Gson();
		return gson.toJson(this);	
	}
	
	
	

}
