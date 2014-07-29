package ru.tata.spring.service.shipment.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.tata.spring.Application;
import ru.tata.spring.model.Supply;
import ru.tata.spring.repository.SupplyRepository;
import ru.tata.spring.service.shipment.SupplyService;
import ru.tata.spring.service.shipment.dto.SupplyTO;

@Service
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SupplyServiceImplTest {

    @Autowired
    private SupplyService supplyService;
    @Autowired
    private SupplyRepository supplyRepository;


    @Before
    public void setUp() throws Exception {
        supplyRepository.save(new Supply("test"));
    }

    @Test(expected = SupplyAlreadyExistException.class)
    public void testImportSupply() throws Exception {
        supplyService.importSupply(new SupplyTO("test"));
    }

    //TODO ...
}