package ru.tata.spring.service.shipment.impl;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tata.spring.repository.SupplyRepository;
import ru.tata.spring.service.exception.BusinessException;
import ru.tata.spring.service.shipment.SupplyService;
import ru.tata.spring.service.shipment.dto.SupplyTO;

import javax.annotation.Nonnull;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyRepository supplyRepository;

    @Override
    public void importSupply(@Nonnull SupplyTO supply) throws BusinessException {
        Preconditions.checkNotNull(supply, "Supply is not defined");
        if (supplyRepository.existByName(supply.getName())) {
            throw new SupplyAlreadyExistException(supply.getName());
        }
        throw new UnsupportedOperationException(); //TODO ...
    }

    @Override
    public void arriveSupply(@Nonnull String name) throws BusinessException {
        Preconditions.checkNotNull(name, "Supply name is not defined");
        throw new UnsupportedOperationException(); //TODO ...
    }

    @Override
    public void activateSupply(@Nonnull String name) throws BusinessException {
        Preconditions.checkNotNull(name, "Supply name is not defined");
        throw new UnsupportedOperationException(); //TODO ...
    }

    @Override
    public void closeSupply(@Nonnull String name) throws BusinessException {
        Preconditions.checkNotNull(name, "Supply name is not defined");
        throw new UnsupportedOperationException(); //TODO ...
    }

    @Override
    public void processSupplyPosition(@Nonnull String name, @Nonnull String article) throws BusinessException {
        Preconditions.checkNotNull(name, "Supply name is not defined");
        Preconditions.checkNotNull(name, "Position article is not defined");
        throw new UnsupportedOperationException(); //TODO ...
    }
}
