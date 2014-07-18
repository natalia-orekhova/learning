package ru.tata.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tata.spring.model.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
}
