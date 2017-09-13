package project.main.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import project.main.entity.ProductOrder;

@RepositoryRestResource(path = "/productsorder")
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Integer>{

	 @Query(value = "SELECT * FROM productorder po inner join product p on p.id  = po.product_id WHERE po.order_id = ?1"
				, nativeQuery = true)
		ProductOrder[] productsByOrder(Integer orderId);
}
