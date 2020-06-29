package com.spring.base.Services;

import java.util.List;

import com.spring.base.Models.Command;

public interface CommandService {
	public int addCommand(Command c);
	public void updateCommand(Command c);
	public List<Command> listCommands();
	public Command getCommandById(int id);
	public List<Command> CommandsByClient(int client_id);
	public void removeCommand(int id);

}
