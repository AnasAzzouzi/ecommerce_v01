package com.spring.base.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.base.DAO.UserDao;
import com.spring.base.Models.User;
@Service
public class UserServiceImp implements UserService{
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	@Transactional
	public void addUser(User u) {
		this.userDao.addUser(u);
	}

	@Override
	@Transactional
	public void updateUser(User u) {
		this.userDao.updateUser(u);
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return this.userDao.listUsers();
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		return this.userDao.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		this.userDao.removeUser(id);
	}
	@Override
	@Transactional
	public User login(String email, String password) {
		return this.userDao.login(email, password);
	}

}
