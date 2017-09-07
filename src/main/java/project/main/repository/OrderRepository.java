package project.main.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import project.main.entity.Orders;

@RepositoryRestResource(path = "/orders")
public interface OrderRepository extends CrudRepository<Orders, Integer>{
	
	@Query(value = "SELECT * FROM orders WHERE user_id = ?1"
			, nativeQuery = true)
	Orders[] orderByUser(Integer user_id);
}
