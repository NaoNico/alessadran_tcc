package project.main.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.GenerationType;

@Entity
@Table(name = "product")
public class Product implements Serializable{

	private static final long serialVersionUID = 4976118010446412459L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	@NotNull
	private Float price;

	@Column(name = "quant")
	@NotNull
	private Integer quant;
	
	@Column(name = "datevalidate")
	@NotNull
	private Date datevalidate;
	
	@Column(name = "status")
	@NotNull
	private String status;
	
	@Column(name = "categoria")
	@NotNull
	private String categoria;
	
	@Column(name = "menu")
	private Integer menu;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuant() {
		return quant;
	}

	public void setQuant(Integer quant) {
		this.quant = quant;
	}

	public Date getDatevalidate() {
		return datevalidate;
	}

	public void setDatevalidate(Date dateValidate) {
		this.datevalidate = dateValidate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getMenu() {
		return menu;
	}

	public void setMenu(Integer menu) {
		this.menu = menu;
	}
}
