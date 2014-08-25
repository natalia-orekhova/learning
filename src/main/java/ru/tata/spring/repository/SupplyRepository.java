package ru.tata.spring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.tata.spring.model.Supply;
import ru.tata.spring.model.SupplyState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

public interface SupplyRepository extends PagingAndSortingRepository<Supply, Long>, SupplyRepositoryCustom {

    @Nullable
    Supply findByName(String name);

    @Nonnull
    List<Supply> findByNameContaining(String name);

    @Nonnull
    List<Supply> findByStateAndCreatedBetween(SupplyState state, Date createdFrom, Date createdTo);

    @Nullable
    Supply findById(long id);
}
