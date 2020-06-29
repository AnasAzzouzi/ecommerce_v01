package com.spring.base.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spring.base.Models.Command;
public class CommandDaoImp implements CommandDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public int addCommand(Command c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);
		return c.getId();
		
	}

	@Override
	public void updateCommand(Command c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Command> listCommands() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Command> commandsList=session.createQuery("from Command").list();
		return commandsList;
		
	}

	@Override
	public Command getCommandById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Command c = (Command) session.load(Command.class, id);
		return c;
	}

	@Override
	public void removeCommand(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Command c = (Command) session.load(Command.class, id);
		if(null != c){
			session.delete(c);
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Command> CommandsByClient(int client_id) {
		Session session = this.sessionFactory.getCurrentSession();
		String query ="from Command where client_id="+client_id;
		List<Command> commandsList=session.createQuery(query).list();
		return commandsList;
	}
}
