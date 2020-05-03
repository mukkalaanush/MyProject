package com.project.ordermanagment.repository;

import com.project.ordermanagment.dto.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Integer> {

}
