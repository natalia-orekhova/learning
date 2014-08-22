package ru.tata.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tata.spring.model.Supply;
import ru.tata.spring.repository.SupplyRepository;

@RestController
@RequestMapping("/supply")
public class SupplyController {
    @Autowired
    private SupplyRepository repository;

    @RequestMapping("/findById/{id}")
    public Supply findById(@PathVariable(value = "id") long id){
        return repository.findById(id);
    }

    @RequestMapping("/findByName/{name}")
    public Supply findByName(@PathVariable(value = "name") String name){
        return repository.findByName(name);
    }
}
