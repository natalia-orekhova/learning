package ru.tata.spring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.tata.spring.model.SupplyPosition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface SupplyPositionRepository extends PagingAndSortingRepository<SupplyPosition, Long>, SupplyPositionRepositoryCustom {

    @Nullable
    SupplyPosition findByArticle(@Nonnull String article);

    @Nullable
    SupplyPosition findById(long id);

}
