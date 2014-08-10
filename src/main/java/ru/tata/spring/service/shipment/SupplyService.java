package ru.tata.spring.service.shipment;

import ru.tata.spring.service.exception.BusinessException;
import ru.tata.spring.service.shipment.dto.SupplyTO;

import javax.annotation.Nonnull;

public interface SupplyService {

    void importSupply(@Nonnull SupplyTO supply) throws BusinessException;

    void arriveSupply(@Nonnull String name) throws BusinessException;

    void activateSupply(@Nonnull String name) throws BusinessException;

    void closeSupply(@Nonnull String name) throws BusinessException;

    void processSupplyPosition(@Nonnull String name, @Nonnull String article) throws BusinessException;

}
