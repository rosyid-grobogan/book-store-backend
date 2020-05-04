package com.rosyid.book.store;

import com.rosyid.book.store.account.entity.EnumRole;
import com.rosyid.book.store.account.entity.Role;
import com.rosyid.book.store.account.repository.RoleRepository;
import com.rosyid.book.store.catalog.entity.Category;
import com.rosyid.book.store.catalog.entity.EnumProductStatus;
import com.rosyid.book.store.catalog.entity.EnumVisibility;
import com.rosyid.book.store.catalog.entity.Product;
import com.rosyid.book.store.catalog.persistence.CatalogEntityPersistence;
import com.rosyid.book.store.catalog.repository.CategoryRepository;
import com.rosyid.book.store.catalog.repository.ProductRepository;
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
	@Autowired
	private ProductRepository productRepository;

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

//	@PostConstruct
//	public void initProduct(){
//		List<Product> products = Stream.of(
//				new Product("Pemrograman Java dengan Spring 4", "pemrograman-java-dengan-spring-4", 1, 85000.00,10, "Tulis detail lagi", 1, EnumProductStatus.FOR_SELL, EnumVisibility.VISIBLE)
//
//		).collect(Collectors.toList() );
//		productRepository.saveAll(products);
//	}
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

}
