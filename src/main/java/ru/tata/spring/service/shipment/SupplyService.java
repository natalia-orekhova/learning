package ru.tata.spring.service.shipment;

import ru.tata.spring.service.exception.BusinessException;
import ru.tata.spring.service.shipment.dto.SupplyTO;

public interface SupplyService {

    void importSupply(SupplyTO supply) throws BusinessException;

    void arriveSupply(String name) throws BusinessException;

    void activateSupply(String name) throws BusinessException;

    void closeSupply(String name) throws BusinessException;

    void processSupplyPosition(String name, String article) throws BusinessException;

}
