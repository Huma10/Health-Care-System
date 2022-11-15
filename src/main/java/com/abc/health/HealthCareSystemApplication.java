package com.abc.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abc.health.dto.UserDTO;
import com.abc.health.service.UserServiceImpl;
import com.abc.health.service.UserServiceInt;
import com.abc.health.util.DataUtility;

@SpringBootApplication
public class HealthCareSystemApplication implements CommandLineRunner {

	@Autowired
	private UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(HealthCareSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(userService.countUser()==0) {
			UserDTO dto = new UserDTO();
			dto.setFirstName("Admin");
			dto.setLastName("Admin");
			dto.setGender("Male");
			dto.setLogin("Admin@admin.com");
			dto.setPassword("Admin@123");
			dto.setMobileNo("888888888");
			dto.setRoleId(1L);
			dto.setRoleName("ADMIN");
			long addAdmin = userService.add(dto);
			System.out.println("Admin added: "+addAdmin);
		}
		
	}
}
