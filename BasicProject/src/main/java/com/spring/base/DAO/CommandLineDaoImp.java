package com.spring.base.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spring.base.Models.CommandLine;

public class CommandLineDaoImp implements CommandLineDao{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addCommandLine(CommandLine cl) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(cl);
		
	}

	@Override
	public void updateCommandLine(CommandLine cl) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(cl);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CommandLine> listCommandLines() {
		Session session = this.sessionFactory.getCurrentSession();
		List<CommandLine> commandLines=session.createQuery("from CommandLine").list();
		return commandLines;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CommandLine getCommandLine(int command_id, int article_id) {
		Session session = this.sessionFactory.getCurrentSession();
		String request="select * from commandLine where command_id="+command_id+" and article_id="+article_id;
		List<CommandLine> commandLines=session.createSQLQuery(request).list();
		return commandLines.get(0);
	}
	@SuppressWarnings("unchecked")

	@Override
	public void removeCommandLine(int command_id, int article_id) {
		Session session = this.sessionFactory.getCurrentSession();
		String request="from Commandline where command_id="+command_id+" and article_id="+article_id;
		List<CommandLine> commandLines=session.createQuery(request).list();
		CommandLine cl=commandLines.get(0);
		if(null != cl){
			session.delete(cl);
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommandLine> CommandLinesByCommand(int command_id) {
		Session session = this.sessionFactory.getCurrentSession();
		String request=" from CommandLine where command_id="+command_id;
		List<CommandLine> commandLines=session.createQuery(request).list();
		return commandLines;
	}

}
