package com.spring.base.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.base.Models.Command;
import com.spring.base.Models.CommandLine;
import com.spring.base.Services.ArticleService;
import com.spring.base.Services.CommandLineService;
import com.spring.base.Services.CommandService;

@RestController
@CrossOrigin(origins="*")
@Transactional
@RequestMapping("/RestCommands")
public class CommandRestController {
	private CommandService commandService;
	private CommandLineService commandLineSevice;
	private ArticleService articleSevice;

	
	@Autowired(required=true)
	@Qualifier(value="articleService")
	public void setArticleSerice(ArticleService as) {
		this.articleSevice=as;
	}
	
	@Autowired(required=true)
	@Qualifier(value="commandService")
	public void setCommandSerice(CommandService cs) {
		this.commandService=cs;
	}
	@Autowired(required=true)
	@Qualifier(value="commandLineService")
	public void setCommandLineSerice(CommandLineService cs) {
		this.commandLineSevice=cs;
	}
	@RequestMapping(value="/addCommand",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addCategory(@RequestBody String data) {
		JsonObject command = new JsonParser().parse(data).getAsJsonObject();
		JsonArray commandLines=command.get("commandLine").getAsJsonArray();
		//getting Command Data 
		
		int client_id=command.get("client_id").getAsInt();
		Date command_date=null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			command_date =formatter.parse(command.get("date").getAsString());

		} catch (ParseException e) {}  

		//Creating a Command 
		Command cmd = new Command();
		cmd.setClient_id(client_id);
		cmd.setCommand_date(command_date);
		int command_id =this.commandService.addCommand(cmd);
		
		
		for (JsonElement cl : commandLines) {
			
			int article_id =cl.getAsJsonObject().get("article_id").getAsInt();
			int quantity=cl.getAsJsonObject().get("quantity").getAsInt();
			CommandLine cmdl= new CommandLine();
			cmdl.setArticle(this.articleSevice.getArticleById(article_id));
			cmdl.setCommand(this.commandService.getCommandById(command_id));
			cmdl.setQuantity(quantity);
			this.commandLineSevice.addCommandLine(cmdl);
		}
		}
		
	@RequestMapping(value="/CommandsByClient",method=RequestMethod.GET)
	public ResponseEntity<String> CommandsByClient(@RequestParam(name="id") String id){
		List<Command> commands=this.commandService.CommandsByClient(Integer.parseInt(id));
		String commandsJson = new Gson().toJson(commands);
		return new ResponseEntity<String>(commandsJson,HttpStatus.OK);


	}
	@RequestMapping(value="/getCommands",method=RequestMethod.GET)
	public ResponseEntity<String> getCommands(){
		List<Command> commands=this.commandService.listCommands();
		String commandsJson = new Gson().toJson(commands);
		return new ResponseEntity<String>(commandsJson,HttpStatus.OK);


	}
	@RequestMapping(value="/CommandLineByCommand",method=RequestMethod.GET)
	public ResponseEntity<String> CommandLineByCommand(@RequestParam(name="id") String id){
		List<CommandLine> commandLines=this.commandLineSevice.CommandLinesByCommand(Integer.parseInt(id));
		System.out.println(commandLines.get(0));
		String commandLinesJson = new Gson().toJson(commandLines);
		return new ResponseEntity<String>(commandLinesJson,HttpStatus.OK);


	}
	

}
