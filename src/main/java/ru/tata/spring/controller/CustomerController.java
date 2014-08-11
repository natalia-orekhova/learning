package ru.tata.spring.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tata.spring.model.Customer;
import ru.tata.spring.repository.CustomerRepository;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

//    @PostConstruct
//    public void initStubData() {
//        if (customerRepository.count() == 0){
//            customerRepository.save(new Customer("Mickey", "Mouse", 86));
//            customerRepository.save(new Customer("Minnie", "Mouse", 86));
//            customerRepository.save(new Customer("Minnie", "Evans", 95));
//            customerRepository.save(new Customer("Jonny", "Evans", 26));
//        }
//    }

    @RequestMapping("/default")
    public Customer getDefault() {
        return customerRepository.findByFirstNameAndLastName("Mickey", "Mouse");
    }

    @RequestMapping("/findByLastName/{lastName}")
    public Iterable<Customer> find(@PathVariable(value = "lastName") String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @RequestMapping("/findByNameAndLastName")
    public Customer findByNameAndLastName(
        @RequestParam(value = "name", required = false, defaultValue = "Jonny") String name,
        @RequestParam(value = "lastName", required = false, defaultValue = "Evans") String lastName
    ){
        return customerRepository.findByFirstNameAndLastName(name, lastName);
    }

    @RequestMapping("/test")
    public Map<String, Object> test(){
       return ImmutableMap.<String, Object>builder()
           .put("hello", "world")
           .put("foo", "bar")
           .build();
    }

}
