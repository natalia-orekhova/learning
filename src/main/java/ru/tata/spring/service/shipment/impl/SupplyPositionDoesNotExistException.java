package ru.tata.spring.service.shipment.impl;

import ru.tata.spring.service.exception.BusinessException;

public class SupplyPositionDoesNotExistException extends BusinessException {
    public SupplyPositionDoesNotExistException(String supplyName, String article){
        super("Article \"%s\" for supply \"%s\" doesn't exists", supplyName, article);
    }
}
