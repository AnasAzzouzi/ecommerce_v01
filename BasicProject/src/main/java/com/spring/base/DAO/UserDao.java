package com.spring.base.DAO;

import java.util.List;

import com.spring.base.Models.User;

public interface UserDao {
	public void addUser(User u);
	public void updateUser(User u);
	public List<User> listUsers();
	public User getUserById(int id);
	public void removeUser(int id);
	public User login (String email,String password);
}
