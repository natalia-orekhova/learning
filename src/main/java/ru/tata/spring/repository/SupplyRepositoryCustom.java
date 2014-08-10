package ru.tata.spring.repository;

import ru.tata.spring.model.Supply;

import javax.annotation.Nonnull;
import java.util.List;

public interface SupplyRepositoryCustom {

    @Nonnull
    List<Supply> getNoClosed();

    @Nonnull
    boolean existByName(@Nonnull String number);
    
}
