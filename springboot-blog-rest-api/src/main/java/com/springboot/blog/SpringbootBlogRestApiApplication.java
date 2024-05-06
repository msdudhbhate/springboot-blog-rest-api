package com.springboot.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.blog.entity.Role;
import com.springboot.blog.repository.RoleRepository;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot Blog App Rest APIs", description = "spring boot blog app rest apis documentation", version = "v1.0", contact = @Contact(name = "mahesh", email = "mahesh1998sitl@gmail.com", url = "https://www.google.com/"), license = @License(name = "Apache 2.0", url = "https://www.google.com/license")), externalDocs = @ExternalDocumentation(description = "spring boot blog app documentation", url = "https://www.google.com/"))
public class SpringbootBlogRestApiApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
		System.out.println("project started");
	}
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);
		
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
		
	}

}
