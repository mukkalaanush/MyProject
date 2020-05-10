package com.project.ordermanagment.service;

import com.project.ordermanagment.dto.Cart;
import com.project.ordermanagment.exception.CartException;
import com.project.ordermanagment.exception.ErrorCode;
import com.project.ordermanagment.exception.OrderProjectException;
import com.project.ordermanagment.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addProductToCart(Cart cart) throws CartException, OrderProjectException {
        if (validateCart(cart)) {
            try {
                Cart found = cartRepository.findByUserIdAndproductId(cart.getUserId(),cart.getProductId());
                cartRepository.save(cart);
            } catch (Exception e) {
                throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
            }
        }
    }

    public List<Cart> findCartByUserId(String userId) throws CartException, OrderProjectException {
        if (userId == null || userId.isEmpty()) {
            throw new CartException(ErrorCode.BAD_DATA, "Valid User Id is required");
        }
        try {
            return cartRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
        }

    }

    public void deleteProductFromCart(String userId, String productId) throws CartException, OrderProjectException {
        if (userId == null || userId.isEmpty()) {
            throw new CartException(ErrorCode.BAD_DATA, "Valid User Id is required");
        }
        if (productId == null || productId.isEmpty()) {
            throw new CartException(ErrorCode.BAD_DATA, "Valid Product Id is required");
        }
        try {
            cartRepository.deleteByUserIdAndProductId(userId, productId);
        } catch (Exception e) {
            throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
        }

    }

    public void updateCart(Cart cart) throws CartException, OrderProjectException {
        if (validateCart(cart)) {
            try {
                cartRepository.deleteByUserIdAndProductId(cart.getUserId(), cart.getProductId());
                cartRepository.save(cart);
            } catch (Exception e) {
                throw new OrderProjectException(ErrorCode.SYSTEM_EXCEPTION, "Exception while writing data to persistant layer", e);
            }
        }
    }


    private Boolean validateCart(Cart cart) {
        if (cart.getQuantity() <= 0) {
            throw new CartException(ErrorCode.BAD_DATA, "Product Quantity has be one or more");
        }
        if (cart.getProductId() == null || cart.getProductId().isEmpty()) {
            throw new CartException(ErrorCode.BAD_DATA, "Valid Product Id is required");
        }
        if (cart.getUserId() == null || cart.getUserId().isEmpty()) {
            throw new CartException(ErrorCode.BAD_DATA, "Valid User Id is required");
        }
        return true;
    }
}
