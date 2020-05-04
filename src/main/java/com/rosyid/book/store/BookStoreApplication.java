package com.rosyid.book.store;

import com.rosyid.book.store.account.entity.EnumRole;
import com.rosyid.book.store.account.entity.Role;
import com.rosyid.book.store.account.repository.RoleRepository;
import com.rosyid.book.store.catalog.entity.Category;
import com.rosyid.book.store.catalog.entity.EnumVisibility;
import com.rosyid.book.store.catalog.repository.CategoryRepository;
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
	@Autowired
	private CategoryRepository categoryRepository;

	@PostConstruct
	public void initRole(){
		List<Role> role = Stream.of(
				new Role(EnumRole.ROLE_USER),
				new Role(EnumRole.ROLE_RESELLER),
				new Role(EnumRole.ROLE_CLIENT),
				new Role(EnumRole.ROLE_ADMIN)
		).collect(Collectors.toList() );
		roleRepository.saveAll(role);
	}

	@PostConstruct
	public void initCategory(){
		List<Category> categories = Stream.of(
				new Category("Komputer", "komputer", null, EnumVisibility.VISIBLE, null),
				new Category("Matematika", "matematika", null, EnumVisibility.VISIBLE, null)
		).collect(Collectors.toList() );
		categoryRepository.saveAll(categories);
	}

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

}
