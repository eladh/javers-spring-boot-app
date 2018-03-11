package com.idi.config;

import com.idi.dao.*;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebConfig {

	private final AccountRepo accountRepo;
	private final PersonRepo personRepo;

	@Autowired
	public WebConfig(AccountRepo accountRepo, PersonRepo personRepo) {
		this.accountRepo = accountRepo;
		this.personRepo = personRepo;
	}

	@Bean
	public Javers javers() {
		return JaversBuilder.javers().build();
	}

	@Bean
	public InitializingBean sendDatabase() {
		return () -> {
			Person person1 = new Person();
			person1.setDescription("front-desk");
			person1.setName("user1");

			Account account1  = new Account();
			account1.setPerson(person1);
			account1.setRole(Role.MODERATOR);
			account1.setPassword("1234");

			personRepo.save(person1);
			accountRepo.save(account1);
		};


	}

}