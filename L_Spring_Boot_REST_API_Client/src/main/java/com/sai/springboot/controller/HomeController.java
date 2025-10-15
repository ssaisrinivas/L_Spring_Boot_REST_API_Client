package com.sai.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.sai.springboot.entity.User;

@Controller("/")
public class HomeController {

	@GetMapping("/home")
	public String homepage(Model model) {

		String viewName = "homepage";

		// Get Request Retrieve Single records
		System.out.println("Get Request Retrieve Single records");
		RestTemplate template = new RestTemplate();
		User user = template.getForObject("http://localhost:8083/K_My_Diary_Spring_Boot_REST_API/users/1", User.class);

		ResponseEntity<User> userEntity = template
				.getForEntity("http://localhost:8083/K_My_Diary_Spring_Boot_REST_API/users/1", User.class);

		System.out.println();
		System.out.println("Response Headers : " + userEntity.getHeaders());
		System.out.println("Response Body : " + userEntity.getBody());
		System.out.println("Status Code : " + userEntity.getStatusCode());
		System.out.println(user.toString());
		System.out.println();

		model.addAttribute("user", user);

		// Get Request Retrieve Multiple records
		System.out.println("Get Request Retrieve Multiple records");
		ResponseEntity<User[]> allUserEntities = template
				.getForEntity("http://localhost:8083/K_My_Diary_Spring_Boot_REST_API/users/", User[].class);

		User users[] = allUserEntities.getBody();

		System.out.println("Response Headers : " + allUserEntities.getHeaders());
		System.out.println("Response Body : " + allUserEntities.getBody().toString());
		System.out.println("Status Code : " + allUserEntities.getStatusCode());

		for (User user2 : users) {
			System.out.println();

			System.out.println(user2.toString());
		}

		// Post Request to save a records
	/*	User us = new User();
		us.setUsername("sai567");
		us.setPassword("sai90");

		System.out.println("Post user response");
		User postUserResponse = template.postForObject("http://localhost:8083/K_My_Diary_Spring_Boot_REST_API/users/",
				us, User.class);
		System.out.println(postUserResponse.toString());
*/
		// Put Request to save a records
		User us1 = new User();
		us1.setId(7);
		us1.setUsername("sai7176");
		us1.setPassword("sai90");

		System.out.println("Put user response");
		template.put("http://localhost:8083/K_My_Diary_Spring_Boot_REST_API/users/7", us1);
		
		// Delete Request to delete a records
		
		System.out.println("Delete user response");
		template.delete("http://localhost:8083/K_My_Diary_Spring_Boot_REST_API/users/18");
		

		return viewName;

	}

}
