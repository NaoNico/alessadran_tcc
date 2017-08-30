package project.main.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import project.main.entity.Product;
import project.main.entity.User;

@RepositoryRestResource(path = "/products")
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	@Query(value = "SELECT * FROM PRODUCT WHERE menu = 1 and datevalidate > CURDATE() and categoria = ?1 group by categoria"
			, nativeQuery = true)
	Product[] productsOnMenu(String categoria);
}