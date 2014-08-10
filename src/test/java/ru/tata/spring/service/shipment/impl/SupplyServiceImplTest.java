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
import ru.tata.spring.model.SupplyState;
import ru.tata.spring.model.Supply;
import ru.tata.spring.model.SupplyPosition;
import ru.tata.spring.repository.SupplyPositionRepository;
import ru.tata.spring.repository.SupplyRepository;
import ru.tata.spring.service.shipment.SupplyService;
import ru.tata.spring.service.shipment.dto.SupplyPositionTO;
import ru.tata.spring.service.shipment.dto.SupplyTO;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


@Service
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SupplyServiceImplTest {

    @Autowired
    private SupplyService supplyService;
    @Autowired
    private SupplyRepository supplyRepository;
    @Autowired
    private SupplyPositionRepository supplyPositionRepository;


    @Before
    public void setUp() throws Exception {
        supplyRepository.save(new Supply("test_exists"));
        saveSupplyForArrive();
        saveArrivedSupply();
        saveActivatedSupply();
        saveClosedSupply();
    }

    private void saveClosedSupply() {
        supplyRepository.save(new Supply("closed_test", SupplyState.CLOSED, new Date(), new Date(), new Date()));
        Supply s = supplyRepository.findByName("closed_test");
        SupplyPosition position = new SupplyPosition(s, "closed_test_article", 2);
        s.getPositions().add(position);
    }

    @Test(expected = SupplyAlreadyExistException.class)
    public void testImportSupplyErrorExists() throws Exception {
        supplyService.importSupply(new SupplyTO("test_exists"));
    }

    @Test
    public void testImportSupplyClear() throws Exception {
        SupplyPositionTO positionTO = new SupplyPositionTO("test_article", 3);
        SupplyTO supplyTO = new SupplyTO("test", positionTO);
        supplyService.importSupply(supplyTO);
        Supply supply = supplyRepository.findByName("test");

        assertThat(supply, notNullValue());
        assertThat(supply.getName(), is(supplyTO.getName()));
        assertThat(supply.getState(), is(SupplyState.CREATED));

        assertThat(supply.getPositions().size(), is(1));
        SupplyPosition position = supply.getPositions().get(0);
        assertThat(position, notNullValue());
        assertThat(position.getArticle(), is(positionTO.getArticle()));
        assertThat(position.getDeclaredAmount(), is(positionTO.getDeclaredAmount()));
        assertThat(position.getAcceptedAmount(), is(0));
    }

    @Test
    public void testArriveSupply() throws Exception {
        supplyService.arriveSupply("arrive_test");
        Supply supply = supplyRepository.findByName("arrive_test");
        assertThat(supply, notNullValue());
        assertThat(supply.getState(), is(SupplyState.ARRIVED));
        assertThat(supply.getArrived(), notNullValue());
    }

    @Test(expected = SupplyWrongStateException.class)
    public void testWrongArriveSupply() throws Exception {
        supplyService.arriveSupply("arrived_test");
    }

    @Test
    public void testActivateSupply() throws Exception {
        supplyService.activateSupply("arrived_test");
        Supply supply = supplyRepository.findByName("arrived_test");
        assertThat(supply, notNullValue());
        assertThat(supply.getState(), is(SupplyState.PROCESSING));
    }

    @Test(expected = SupplyWrongStateException.class)
    public void testWrongActivateSupply() throws Exception {
        supplyService.activateSupply("activated_test");
    }

    @Test
    public void testCloseSupply() throws Exception {
        supplyService.closeSupply("activated_test");
        Supply supply = supplyRepository.findByName("activated_test");
        assertThat(supply, notNullValue());
        assertThat(supply.getState(), is(SupplyState.CLOSED));
    }

    @Test(expected = SupplyWrongStateException.class)
    public void testWrongCloseSupply() throws Exception {
        supplyService.closeSupply("closed_test");
    }

    @Test
    public void testProcessSupplyPosition() throws Exception {
        int oldAcceptedAmount = supplyPositionRepository.findByArticle("activated_test_article").getAcceptedAmount();
        supplyService.processSupplyPosition("activated_test", "activated_test_article");
        SupplyPosition sp = supplyPositionRepository.findByArticle("activated_test_article");
        assertThat(sp, notNullValue());
        assertThat(sp.getAcceptedAmount(), is(oldAcceptedAmount + 1));
    }

    @Test(expected = SupplyWrongStateException.class)
    public void testWrongProcessSupplyPosition() throws Exception {
        supplyService.processSupplyPosition("arrive_test", "arrive_test_article");
    }

    private void saveActivatedSupply() {
        supplyRepository.save(new Supply("activated_test", SupplyState.PROCESSING, new Date(), new Date(), null));
        Supply s = supplyRepository.findByName("activated_test");
        SupplyPosition position = new SupplyPosition(s, "activated_test_article", 2);
        supplyPositionRepository.save(position);
        s.getPositions().add(position);
    }

    private void saveArrivedSupply() {
        supplyRepository.save(new Supply("arrived_test", SupplyState.ARRIVED, new Date(), new Date(), null));
        Supply s = supplyRepository.findByName("arrived_test");
        SupplyPosition position = new SupplyPosition(s, "arrived_test_article", 2);
        supplyPositionRepository.save(position);
        s.getPositions().add(position);
    }

    private void saveSupplyForArrive() {
        supplyRepository.save(new Supply("arrive_test", SupplyState.CREATED, new Date(), null, null));
        Supply s = supplyRepository.findByName("arrive_test");
        SupplyPosition position = new SupplyPosition(s, "arrive_test_article", 2);
        supplyPositionRepository.save(position);
        s.getPositions().add(position);
    }
}