package com.project.ordermanagment.repository;

import com.project.ordermanagment.dto.OrderProductMap;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderProductMapRepository extends CrudRepository<OrderProductMap, Integer> {
       void deleteByOrderId(String orderId);

       List<OrderProductMap>  getOrderProductMapByOrderId(String orderId);
}
