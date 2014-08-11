package ru.tata.spring.service.shipment.impl;

import ru.tata.spring.service.exception.BusinessException;

public class SupplyPositionAlreadyExistException extends BusinessException {
    public SupplyPositionAlreadyExistException(String article) {
        super("Supply position with article \"%s\" already exist", article);
    }
}
