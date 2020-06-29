package com.spring.base.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.base.DAO.CommandDao;
import com.spring.base.Models.Command;
@Service
public class CommandServiceImp implements CommandService{
	private CommandDao commandDao;
	

	public void setCommandDao(CommandDao commandDao) {
		this.commandDao = commandDao;
	}

	@Override
	@Transactional
	public int addCommand(Command c) {
		return this.commandDao.addCommand(c);		
	}

	@Override
	@Transactional
	public void updateCommand(Command c) {
		this.commandDao.updateCommand(c);
	}

	@Override
	@Transactional
	public List<Command> listCommands() {
		return this.commandDao.listCommands();
	}

	@Override
	@Transactional
	public Command getCommandById(int id) {
		return this.commandDao.getCommandById(id);
	}

	@Override
	@Transactional
	public void removeCommand(int id) {
		this.commandDao.removeCommand(id);
	}

	@Override
	@Transactional
	public List<Command> CommandsByClient(int client_id) {
		return this.commandDao.CommandsByClient(client_id);
	}
	

}
