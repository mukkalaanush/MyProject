package com.project.ordermanagment.repository;

import com.project.ordermanagment.dto.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Integer> {
      List<Cart> findByUserId(String userId);

      void deleteByUserIdAndProductId(String userId, String productId);

      Cart findByUserIdAndproductId(String userId, String productId);

}
