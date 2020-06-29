package com.spring.base.DAO;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.spring.base.Models.User;

public class UserDaoImp implements UserDao{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	
	@Override
	public void addUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
		
	}

	@Override
	public void updateUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> usersList = session.createQuery("from User").list();
		return usersList;
	}

	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User u = (User) session.load(User.class, id);
		return u;	
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, id);
		if(null != u){
			session.delete(u);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public User login(String email, String password) {	
		Session session = this.sessionFactory.getCurrentSession();
		String query="from User where email='"+email+"' and password='"+password+"'";
		List<User> usersList = session.createQuery(query).list();
		return usersList.get(0);
	}

}
