package ru.tata.spring.repository;


import javax.annotation.Nonnull;

public interface SupplyPositionRepositoryCustom {

    @Nonnull
    boolean existByArticle(@Nonnull String article);

}
