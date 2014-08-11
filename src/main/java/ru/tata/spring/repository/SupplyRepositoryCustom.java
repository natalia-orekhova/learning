package ru.tata.spring.repository;

import ru.tata.spring.model.Supply;
import ru.tata.spring.model.SupplyPosition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface SupplyRepositoryCustom {

    @Nonnull
    List<Supply> getNoClosed();

    @Nonnull
    boolean existByName(@Nonnull String number);

}
