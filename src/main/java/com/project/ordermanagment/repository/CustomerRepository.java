package com.project.ordermanagment.repository;

import com.project.ordermanagment.dto.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
