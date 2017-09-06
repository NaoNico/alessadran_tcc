package project.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import project.main.entity.Orders;

@RepositoryRestResource(path = "/orders")
public interface OrderRepository extends CrudRepository<Orders, Integer>{

}
