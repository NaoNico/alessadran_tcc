package project.main.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productorder")
public class ProductOrder implements Serializable{

	private static final long serialVersionUID = 5852872093332024795L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "quant")
	@NotNull
	private Integer quant;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "price")
	@NotNull
	private Float price;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders orders;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuant() {
		return quant;
	}

	public void setQuant(Integer quant) {
		this.quant = quant;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrder(Orders orders) {
		this.orders = orders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
