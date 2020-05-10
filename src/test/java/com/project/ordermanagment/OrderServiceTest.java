package com.project.ordermanagment;

import com.mmnaseri.utils.spring.data.dsl.mock.RepositoryMockBuilder;
import com.project.ordermanagment.repository.CartRepository;
import com.project.ordermanagment.repository.OrderProductMapRepository;
import com.project.ordermanagment.repository.OrderRepository;
import com.project.ordermanagment.service.OrderService;
import org.testng.annotations.BeforeMethod;

public class OrderServiceTest {

    private OrderService service;

    @BeforeMethod
    public void setUp() {
        final OrderRepository orderRepository = new RepositoryMockBuilder().mock(OrderRepository.class);
        final CartRepository cartRepository = new RepositoryMockBuilder().mock(CartRepository.class);
        final OrderProductMapRepository productRepository = new RepositoryMockBuilder().mock(OrderProductMapRepository.class);
        service = new OrderService(orderRepository, cartRepository, productRepository);
    }

}
