package ru.tata.spring.service.shipment.impl;

import ru.tata.spring.model.SupplyState;
import ru.tata.spring.service.exception.BusinessException;

public class SupplyWrongStateException extends BusinessException {
    public SupplyWrongStateException(String name, SupplyState supplyState, SupplyState state) {
        super("Supply with name \"%s\" has state \"%s\". Should be \"%s\" ", name, supplyState, state);
    }
}
