package ru.tata.spring.repository;

import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.tata.spring.Application;
import ru.tata.spring.model.State;
import ru.tata.spring.model.Supply;
import ru.tata.spring.model.SupplyPosition;

import java.util.Date;
import java.util.List;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SupplyRepositoryTest {

    @Autowired
    private SupplyRepository repository;

    private Supply supply1;
    private Supply supply2;
    private Supply supply3;

    @Before
    public void setUp() throws Exception {
        supply1 = repository.save(new Supply("supply1", State.CREATED, new Date(), null, null, new SupplyPosition("suppos1", 1, 0)));
        supply2 = repository.save(new Supply("supply2", State.PROCESSING, new Date(), new Date(), new Date(), new SupplyPosition( "suppos2", 1, 0)));
        supply3 = repository.save(new Supply("supply3", State.CLOSED, new Date(), new Date(), new Date(), new SupplyPosition( "suppos3", 1, 0)));
    }

    @Test
    public void testFindByName() throws Exception {
        Supply sp = repository.findByName("supply1");
        Assert.assertThat(sp, Matchers.is(supply1));
    }

    @Test
    public void testFindByNameContaining() throws Exception {
        List<Supply> sp = repository.findByNameContaining("supply1");
        Assert.assertThat(sp, Matchers.containsInAnyOrder(supply1));
    }

    @Test
    public void testFindByStateAndCreatedBetween() throws Exception {
        List<Supply> sp = repository.findByStateAndCreatedBetween(State.CREATED, new DateTime().minusMonths(1).toDate(), new Date());
        Assert.assertThat(sp, Matchers.containsInAnyOrder(supply1));
    }

    @Test
    public void testCustomSelect() throws Exception {
        List<Supply> sp = repository.getNoClosed();
        Assert.assertThat(sp, Matchers.containsInAnyOrder(supply1, supply2));
    }
}
