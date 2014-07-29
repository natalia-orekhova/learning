package ru.tata.spring.service.shipment.impl;

import ru.tata.spring.service.exception.BusinessException;

public class SupplyAlreadyExistException extends BusinessException {
    public SupplyAlreadyExistException(String name) {
        super("Supply with name \"%s\" already exists", name);
    }
}
