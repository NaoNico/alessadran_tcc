package project.main.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import project.main.entity.User;

@RepositoryRestResource(path = "/users")
public interface UserRepository extends CrudRepository<User, Integer>{
	
	 @Query(value = "SELECT * FROM user WHERE username = ?1 and password = ?2", nativeQuery = true)
	 User findByUserAndPassword(String userName, String password);
}