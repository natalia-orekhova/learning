package ru.tata.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tata.spring.model.SupplyPosition;
import ru.tata.spring.repository.SupplyPositionRepository;

import java.util.List;

@RestController
@RequestMapping("/supplyPosition")
public class SupplyPositionController {
    @Autowired
    SupplyPositionRepository repository;

    @RequestMapping("/findById/{id}")
    public SupplyPosition findById(@PathVariable(value = "id") long id){
        return repository.findById(id);
    }

    @RequestMapping("findByShipmentId/{id}")
    public List<SupplyPosition> findBySupply(@PathVariable(value = "id") long id){
        return repository.findBySupply(id);
    }

    @RequestMapping("/findByShipmentIdAndArticle")
    public SupplyPosition findBySupplyAndArticle(
            @RequestParam(value = "supplyId", required = true) long id,
            @RequestParam(value = "article", required = true) String article){
        return repository.findBySupplyIdAndArticle(id, article);
    }
}
