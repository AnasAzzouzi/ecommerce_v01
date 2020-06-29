package com.spring.base.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.base.DAO.CommandLineDao;
import com.spring.base.Models.CommandLine;
@Service
public class CommandLineServiceImp implements CommandLineService {
	private CommandLineDao commandLineDao;

	public void setCommandLineDao(CommandLineDao commandLineDao) {
		this.commandLineDao = commandLineDao;
	}

	@Override
	@Transactional
	public void addCommandLine(CommandLine cl) {
		this.commandLineDao.addCommandLine(cl);		
	}

	@Override
	@Transactional
	public void updateCommandLine(CommandLine cl) {
		this.commandLineDao.updateCommandLine(cl);
	}

	@Override
	@Transactional
	public List<CommandLine> listCommandLines() {
		return this.commandLineDao.listCommandLines();
	}

	@Override
	@Transactional
	public CommandLine getCommandLine(int command_id, int article_id) {
		return this.commandLineDao.getCommandLine(command_id, article_id);
	}

	@Override
	@Transactional
	public void removeCommandLine(int command_id, int article_id) {
		this.commandLineDao.removeCommandLine(command_id, article_id);
	}

	@Override
	@Transactional
	public List<CommandLine> CommandLinesByCommand(int command_id) {
		return this.commandLineDao.CommandLinesByCommand(command_id);
	}

}
