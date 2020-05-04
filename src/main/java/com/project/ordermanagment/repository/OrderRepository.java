package com.project.ordermanagment.repository;

import com.project.ordermanagment.dto.Orders;
import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface OrderRepository extends CrudRepository<Orders, Integer> {

    Orders findByOrderId(String orderId);

    void deleteByOrderId(String orderId);


}
