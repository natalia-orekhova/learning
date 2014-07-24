package ru.tata.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tata.spring.model.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    //@Query("select customer from Customer customer where customer.lastName = :arg1")
    List<Customer> findByLastName(String lastName);

    Customer findByFirstNameAndLastName(String firstName, String lastName);

    List<Customer> findByAgeBetween(int moreThen, int lessThen);

    //Поиск списка людей по имени или фамилии
    List<Customer> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);
}
