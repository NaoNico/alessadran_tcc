package project.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import project.main.entity.ProductOrder;

@RepositoryRestResource(path = "/productsorder")
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Integer>{

}
