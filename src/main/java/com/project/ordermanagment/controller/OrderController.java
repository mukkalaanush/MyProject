package com.project.ordermanagment.controller;

import com.project.ordermanagment.dto.Cart;
import com.project.ordermanagment.dto.Customer;
import com.project.ordermanagment.dto.OrderProductMap;
import com.project.ordermanagment.dto.Orders;
import com.project.ordermanagment.repository.CartRepository;
import com.project.ordermanagment.repository.CustomerRepository;
import com.project.ordermanagment.repository.OrderProductMapRepository;
import com.project.ordermanagment.repository.OrderRepository;
import com.project.ordermanagment.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/api/v1/orders") // This means URL's start with /demo (after Application path)
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private OrderProductMapRepository orderProductMapRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@PostMapping(path="/addtocart") // Map ONLY POST Requests
	public ResponseEntity<Result> addItemToCart(@RequestBody Cart cart) {
		cartRepository.save(cart);

		return new ResponseEntity<>(new Result("Added To Cart", "Completed"), HttpStatus.OK);
	}

	@GetMapping(path="/cart/products/{userId}")
	public ResponseEntity<Iterable<Cart>> getProductsFromCart(@PathVariable String userId) {
		// This returns a JSON or XML with the user
		Iterable<Cart> cart = cartRepository.findByUserId(userId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}

	@PutMapping(path="/addtocart")
	public ResponseEntity<Result> updateProductsFromCart(@RequestBody Cart cart) {
		// This returns a JSON or XML with the user
		cartRepository.deleteByUserIdAndProductId(cart.getUserId(),cart.getProductId());
		cartRepository.save(cart);
		return new ResponseEntity<>(new Result("Product Updated", "Completed"),HttpStatus.OK);
	}

	@DeleteMapping(path="/{orderId}")
	public ResponseEntity<Void> removeOrder(@PathVariable String orderId) {
		// This returns a JSON or XML with the user
		orderRepository.deleteByOrderId(orderId);
		orderProductMapRepository.deleteByOrderId(orderId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path="/{userId}/{productId}")
	public ResponseEntity<Void> removeProductFromCart(@PathVariable String userId, @PathVariable String productId) {
		// This returns a JSON or XML with the user
		cartRepository.deleteByUserIdAndProductId(userId,productId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@PostMapping(path="/createOrder") // Map ONLY POST Requests
	public ResponseEntity<Result> addNewOrder (@RequestBody Orders order) {
		String orderId = UUID.randomUUID().toString();
		order.setOrderId(orderId);
		orderRepository.save(order);
		List<Cart> products = cartRepository.findByUserId(order.getUserId());
		for(Cart cart : products) {
			OrderProductMap map = new OrderProductMap();
			map.setOrderId(orderId);
			map.setProductStatus(0);
			map.setProductId(cart.getProductId());
			map.setGiftStatus(0);
			map.setProductUIN("somethng");
			orderProductMapRepository.save(map);
		}
		return new ResponseEntity<>(new Result(orderId, "Created"), HttpStatus.OK);
	}
	@PostMapping(path="/products/{orderId}") // Map ONLY POST Requests
	public ResponseEntity<String> addProducts(@RequestBody List<Cart> products, @PathVariable String orderId) {
		for(Cart cart : products) {
			OrderProductMap map = new OrderProductMap();
			map.setOrderId(orderId);
			map.setProductStatus(0);
			map.setProductId(cart.getProductId());
			map.setGiftStatus(0);
			map.setProductUIN("somethng");
			orderProductMapRepository.save(map);
		}
		return new ResponseEntity<>(orderId, HttpStatus.OK);
	}

	@GetMapping(path="/order")
	public ResponseEntity<Orders> getOrderByID(@RequestParam String orderId) {
		// This returns a JSON or XML with the user
		Orders order = orderRepository.findByOrderId(orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}

	@GetMapping(path="/all")
	public ResponseEntity<Iterable<Orders>> getAllOrders() {
		// This returns a JSON or XML with the users
		 Iterable<Orders> orders = orderRepository.findAll();
		 return new ResponseEntity<>(orders,HttpStatus.OK);
	}

	@GetMapping(path="/users")
	public ResponseEntity<Iterable<Customer>> getUsers() {
		// This returns a JSON or XML with the users
		Iterable<Customer> users = customerRepository.findAll();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
}
