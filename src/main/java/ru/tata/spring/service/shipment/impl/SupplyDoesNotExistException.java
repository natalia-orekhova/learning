package ru.tata.spring.service.shipment.impl;

import ru.tata.spring.service.exception.BusinessException;

public class SupplyDoesNotExistException extends BusinessException {
    public SupplyDoesNotExistException(String name){
        super("Supply with name \"%s\" doesn't exists", name);
    }
}
