package ru.tata.spring.repository;

import ru.tata.spring.model.Supply;

import java.util.List;

public interface SupplyRepositoryCustom {
    List<Supply> getNoClosed();
}
