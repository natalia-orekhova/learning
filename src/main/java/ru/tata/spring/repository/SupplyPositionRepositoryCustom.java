package ru.tata.spring.repository;


import ru.tata.spring.model.SupplyPosition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface SupplyPositionRepositoryCustom {

    @Nonnull
    boolean existByArticle(@Nonnull String article);

    @Nullable
    SupplyPosition findByArticleAndSupplyName(String supplyName, String article);
}
