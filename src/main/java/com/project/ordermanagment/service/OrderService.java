package com.project.ordermanagment.service;

import com.project.ordermanagment.dto.Cart;
import com.project.ordermanagment.dto.OrderProductMap;
import com.project.ordermanagment.dto.Orders;
import com.project.ordermanagment.exception.ErrorCode;
import com.project.ordermanagment.exception.OrderException;
import com.project.ordermanagment.exception.OrderProjectException;
import com.project.ordermanagment.repository.CartRepository;
import com.project.ordermanagment.repository.OrderProductMapRepository;
import com.project.ordermanagment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {


    private OrderRepository orderRepository;
    private CartRepository cartRepository;
    private OrderProductMapRepository orderProductMapRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, OrderProductMapRepository orderProductMapRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.orderProductMapRepository = orderProductMapRepository;
    }


    public String createNewOrder(Orders order) throws OrderException, OrderProjectException {
        if (validateOrder(order)) {
            try {
                String orderId = UUID.randomUUID().toString();
                order.setOrderInitiateTime(new Date());
                order.setOrderId(orderId);
                orderRepository.save(order);
                List<Cart> products = cartRepository.findByUserId(order.getUserId());
                for (Cart cart : products) {
                    OrderProductMap map = new OrderProductMap();
                    map.setOrderId(orderId);
                    map.setProductStatus(0);
                    map.setProductId(cart.getProductId());
                    map.setGiftStatus(0);
                    map.setProductUIN("somethng");
                    orderProductMapRepository.save(map);
                    cartRepository.deleteByUserIdAndProductId(order.getUserId(), cart.getProductId());
                }
                return orderId;
            } catch (Exception e) {
                throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
            }
        }
        throw new OrderException(ErrorCode.BAD_DATA, "Valid date is required");

    }

    public void removeOrder(String orderId) throws OrderException, OrderProjectException {
        if (orderId == null || orderId.isEmpty()) {
            throw new OrderException(ErrorCode.BAD_DATA, "Valid Order Id is required");
        }
        try {
            orderRepository.deleteByOrderId(orderId);
            orderProductMapRepository.deleteByOrderId(orderId);
        } catch (Exception e) {
            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
        }
    }

    public Orders findOrder(String orderId) throws OrderException, OrderProjectException {
        if (orderId == null || orderId.isEmpty()) {
            throw new OrderException(ErrorCode.BAD_DATA, "Valid Order Id is required");
        }
        try {
            return orderRepository.findByOrderId(orderId);

        } catch (Exception e) {
            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
        }
    }

    public Iterable<Orders> getAllOders() throws OrderProjectException {
        try {
            Iterable<Orders> orders = orderRepository.findAll();
            for (Orders order : orders) {
                List<OrderProductMap> products = orderProductMapRepository.getOrderProductMapByOrderId(order.getOrderId());
                order.setProducts(products);
            }
            return orders;
        } catch (Exception e) {
            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
        }
    }

    private Boolean validateOrder(Orders order) throws OrderException {
        if (order.getAddressId() == null || order.getAddressId().isEmpty()) {
            throw new OrderException(ErrorCode.BAD_DATA, "Valid Address Id is required");
        }
        if (order.getUserId() == null || order.getUserId().isEmpty()) {
            throw new OrderException(ErrorCode.BAD_DATA, "Valid User Id is required");
        }
        return true;
    }

}
