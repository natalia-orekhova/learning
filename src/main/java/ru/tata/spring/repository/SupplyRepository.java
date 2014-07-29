package ru.tata.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tata.spring.model.State;
import ru.tata.spring.model.Supply;

import java.util.Date;
import java.util.List;

public interface SupplyRepository extends CrudRepository<Supply, Long>, SupplyRepositoryCustom{

    Supply findByName(String name);

    List<Supply> findByNameContaining(String name);

    List<Supply> findByStateAndCreatedBetween(State state, Date createdFrom, Date createdTo);
}
