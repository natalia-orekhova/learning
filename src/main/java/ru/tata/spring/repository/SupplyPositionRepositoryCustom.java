package ru.tata.spring.repository;


import ru.tata.spring.model.SupplyPosition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface SupplyPositionRepositoryCustom {

    @Nonnull
    boolean existByArticle(@Nonnull String article);

    @Nullable
    SupplyPosition findByArticleAndSupplyName(String supplyName, String article);

    @Nonnull
    List<SupplyPosition> findBySupply(long id);

    @Nullable
    SupplyPosition findBySupplyIdAndArticle(long id, String article);
}
