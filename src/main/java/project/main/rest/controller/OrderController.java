package project.main.rest.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AutoConfigurationReportEndpoint.Report;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import project.main.pojo.OrderPojo;
import project.main.pojo.ProductOrderPojo;
import project.main.repository.OrderRepository;
import project.main.repository.ProductOrderRepository;
import project.main.repository.ProductRepository;
import project.main.repository.UserRepository;

@RestController
@CrossOrigin(maxAge = 3600)
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
		order.setDate(field.get("date").asText());
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
		    productOrder.setName(slaidNode.get("name").asText());
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
	
	@RequestMapping(path = "/orderbyuser/{id}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> orderByUser(@PathVariable Integer id){
		System.out.println("estou aq");
		Orders[] orders = orderRepository.orderByUser(id);
		ArrayList<OrderPojo> orderPojo = new ArrayList<OrderPojo>();
		for (Orders order : orders) {
			OrderPojo orderPojo2 = new OrderPojo();
			orderPojo2.id = order.getId();
			orderPojo2.price = order.getPrice();
			orderPojo2.status = order.getStatus();
			orderPojo2.date = order.getDate();
			orderPojo.add(orderPojo2);
		}
		return new ResponseEntity<ArrayList<OrderPojo>>(orderPojo, HttpStatus.OK);
	}

	@RequestMapping(path = "/productsbyorder/{id}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> productsByOrder(@PathVariable Integer id){
		ProductOrder[] orders = productOrderRepository.productsByOrder(id);
		ArrayList<ProductOrderPojo> productsOrderPojo = new ArrayList<ProductOrderPojo>();
		for (ProductOrder productOrder : orders) {
			ProductOrderPojo productOrderPojo = new ProductOrderPojo();
			productOrderPojo.name = productOrder.getName();
			productOrderPojo.price = productOrder.getPrice();
			productOrderPojo.quant = productOrder.getQuant();
			productsOrderPojo.add(productOrderPojo);
		}
		return new ResponseEntity<ArrayList<ProductOrderPojo>>(productsOrderPojo, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/allorders", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<?> orders(){
		Iterable<Orders> orders = orderRepository.findAll();
		ArrayList<OrderPojo> orderPojo = new ArrayList<OrderPojo>();
		for (Orders order : orders) {
			OrderPojo orderPojo2 = new OrderPojo();
			orderPojo2.id = order.getId();
			orderPojo2.price = order.getPrice();
			orderPojo2.status = order.getStatus();
			orderPojo2.date = order.getDate();
			orderPojo2.userName = order.getUser().getUsername();
			orderPojo.add(orderPojo2);
		}
		return new ResponseEntity<ArrayList<OrderPojo>>(orderPojo, HttpStatus.OK);
	}
}
