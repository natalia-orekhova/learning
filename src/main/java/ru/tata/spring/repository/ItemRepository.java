package ru.tata.spring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.tata.spring.model.Item;

import javax.annotation.Nullable;

@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository  extends PagingAndSortingRepository<Item, Long> {

    @Nullable
    Item findById(@Param("id")long id);

}
