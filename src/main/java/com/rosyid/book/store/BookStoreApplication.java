package com.rosyid.book.store;

import com.rosyid.book.store.entity.EnumRole;
import com.rosyid.book.store.entity.Role;
import com.rosyid.book.store.entity.User;
import com.rosyid.book.store.repository.RoleRepository;
import com.rosyid.book.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class BookStoreApplication {

	@Autowired
	private RoleRepository roleRepository;

	@PostConstruct
	public void initRole(){
		List<Role> role = Stream.of(
				new Role(EnumRole.ROLE_USER),
				new Role(EnumRole.ROLE_CLIENT),
				new Role(EnumRole.ROLE_ADMIN)
		).collect(Collectors.toList() );
		roleRepository.saveAll(role);
	}

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

}
