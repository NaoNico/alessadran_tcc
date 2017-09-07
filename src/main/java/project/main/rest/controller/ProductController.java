package project.main.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import project.main.entity.Product;
import project.main.repository.ProductRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class ProductController {
	
	@Autowired
	ProductRepository repository;
	
	@RequestMapping(path = "/products/{id}", method = RequestMethod.PUT)
	@ResponseBody
	ResponseEntity<?> changeStatus(@PathVariable Integer id, @RequestBody ObjectNode field){
		
		Product product = repository.findOne(id);
		if (product == null){
			return new ResponseEntity<String>("Invalid Product", HttpStatus.BAD_GATEWAY);
		}
		if(field.has("status")){
			product.setStatus(field.get("status").asText());
		}else 
			if (field.has("quant")) {
				product.setQuant(field.get("quant").asInt());
			} else 
				if (field.has("menu")) {
					product.setMenu(field.get("menu").asInt());
				}
			
		repository.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> changeStatus(@PathVariable Integer id){
		return new ResponseEntity<Product>(repository.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/products/menu/{categoria}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> productsOnMenu(@PathVariable String categoria){
		Product[] product;
		product = repository.productsOnMenu(categoria);
		return new ResponseEntity<Product[]>(product, HttpStatus.OK);
	}
	

	@RequestMapping(path = "/products/menu", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> productsOnMenuWithoutCategoria(){
		Product[] product;
		product = repository.productsOnMenuWithoutCategoria();
		return new ResponseEntity<Product[]>(product, HttpStatus.OK);
	}
}
	