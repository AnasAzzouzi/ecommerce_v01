package com.spring.base.DAO;

import java.util.List;

import com.spring.base.Models.CommandLine;

public interface CommandLineDao {
	public void addCommandLine(CommandLine cl);
	public void updateCommandLine(CommandLine cl);
	public List<CommandLine> listCommandLines();
	public List<CommandLine> CommandLinesByCommand(int command_id);
	public CommandLine getCommandLine(int command_id,int article_id);
	public void removeCommandLine(int command_id,int article_id);

}
