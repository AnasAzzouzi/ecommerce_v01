package com.spring.base.Controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



import com.spring.base.Services.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.base.Models.User;


@RestController
@CrossOrigin(origins="*")
@Transactional
@RequestMapping("/RestUsers")
public class UserRestController {
private UserService userService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService us){
		this.userService = us;
	}
	
	@RequestMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<String>getUsers() {
		List<User> users=this.userService.listUsers();
		String usersJson = new Gson().toJson(users);
		
        return new ResponseEntity<String>(usersJson,HttpStatus.OK);
    }
	@RequestMapping(value = "/login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<String>login(@RequestBody String data) {
		JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
		String email=jsonObject.get("email").getAsString();
		String password=jsonObject.get("password").getAsString();
		User user=this.userService.login(email, password);
		String usersJson = new Gson().toJson(user);
		
        return new ResponseEntity<String>(usersJson,HttpStatus.OK);
    }

	@RequestMapping(value = "/setUser", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
		public void setUser(@RequestBody String data){
		this.userService.addUser(User.CreateUserFromJson(data));
		}
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody String data){
		User u =User.CreateUserFromJson(data);
		this.userService.updateUser(u);
		u =this.userService.getUserById(u.getId());
		String usersJson = new Gson().toJson(u);
        return new ResponseEntity<String>(usersJson,HttpStatus.OK);

		
	}
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updatePassword(@RequestBody String data){
		JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
		System.out.println(jsonObject);
		int user_id=jsonObject.get("user_id").getAsInt();
		String oldPassword=jsonObject.get("oldPassword").getAsString();
		String newPassword=jsonObject.get("newPassword").getAsString();
		User u =this.userService.getUserById(user_id);
		System.out.println(u.getPassword());
		if(u.getPassword().compareTo(oldPassword)==0) {
			u.setPassword(newPassword);
			this.userService.updateUser(u);
			return new ResponseEntity<String>("Updated",HttpStatus.OK);
			
		}
		else{
			return new ResponseEntity<String>("Wrong Password !",HttpStatus.OK);
		}
		
	}
}
