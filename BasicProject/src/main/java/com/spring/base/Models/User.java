package com.spring.base.Models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Entity
@Table(name="User")
public class User {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String postcode;
	private String address;
	private String city;
	private String tel;
	private String profile="Client";

	public User() {}

	public User(int id, String first_name, String last_name, String email,String password,String postcode, String address, String city, String tel,String profile) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password=password;
		this.postcode = postcode;
		this.address = address;
		this.city = city;
		this.tel = tel;
		this.profile=profile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public static User CreateUserFromJson(String userJson) {
		User user=new User();
		JsonObject jsonObject = new JsonParser().parse(userJson).getAsJsonObject();
		try {
			user.setId(jsonObject.get("id_user").getAsInt());
		}catch(Exception e) {}
		try {
			user.setFirst_name(jsonObject.get("first_name").getAsString());
		}catch(Exception e) {}
		try {
			user.setLast_name(jsonObject.get("last_name").getAsString());
		}catch(Exception e) {}
		try {
			user.setEmail(jsonObject.get("email").getAsString());
		}catch(Exception e) {}
		try {
			user.setPassword(jsonObject.get("password").getAsString());
		}catch(Exception e) {}
		try {
			user.setPostcode(jsonObject.get("postcode").getAsString());
		}catch(Exception e) {}
		try {
			user.setAddress(jsonObject.get("address").getAsString());
		}catch(Exception e) {}
		try {
			user.setCity(jsonObject.get("city").getAsString());
		}catch(Exception e) {}
		try {
			user.setTel(jsonObject.get("tel").getAsString());
		}catch(Exception e) {}
		try {
			user.setProfile(jsonObject.get("profile").getAsString());
		}catch(Exception e) {}
				
				return user;	
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email +" postcode="+ postcode + ", address=" + address + ", city=" + city + ", tel=" + tel + "]";
	}
	public String toJson() {
		Gson gson= new Gson();
		return gson.toJson(this);	
	}
	
}
	

