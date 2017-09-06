package project.main.rest.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AutoConfigurationReportEndpoint.Report;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import project.main.entity.Orders;
import project.main.entity.Product;
import project.main.entity.ProductOrder;
import project.main.repository.OrderRepository;
import project.main.repository.ProductOrderRepository;
import project.main.repository.ProductRepository;
import project.main.repository.UserRepository;

@RestController
public class OrderController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductOrderRepository productOrderRepository;
	
	@RequestMapping(path = "/order", method = RequestMethod.POST)
	@ResponseBody
	ResponseEntity<?> createOrder(@RequestBody ObjectNode field){
		Orders order = new Orders();
		order.setPrice((float) field.get("price").asDouble());
		order.setStatus(field.get("status").asText());
		order.setUser(userRepository.findOne(field.get("user_id").asInt()));
		order = orderRepository.save(order);
		
		ArrayNode products = (ArrayNode) field.get("products");
		Iterator<JsonNode> slaidsIterator = products.elements();
		while (slaidsIterator.hasNext()) {
		    JsonNode slaidNode = slaidsIterator.next();
		    ProductOrder productOrder = new ProductOrder();
		    productOrder.setOrder(order);
		    productOrder.setPrice((float) slaidNode.get("price").asDouble());
		    productOrder.setQuant(slaidNode.get("quant").asInt());
		    productOrder.setProduct(productRepository.findOne(slaidNode.get("product_id").asInt()));
		    productOrderRepository.save(productOrder);
		}
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	
	@RequestMapping(path = "/order/{id}", method = RequestMethod.PUT)
	@ResponseBody
	ResponseEntity<?> changeStatus(@PathVariable Integer id, @RequestBody ObjectNode field){
		Orders order = orderRepository.findOne(id);
		if (order == null){
			return new ResponseEntity<String>("Invalid Order", HttpStatus.BAD_GATEWAY);
		}
		if(field.has("status")){
			order.setStatus(field.get("status").asText());
		}
		
		orderRepository.save(order);
		return new ResponseEntity<String>("changed with success", HttpStatus.OK);
	}
}
