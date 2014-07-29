package ru.tata.spring.repository;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.tata.spring.Application;
import ru.tata.spring.model.Customer;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    private Customer jackBauer;
    private Customer chloeOBrian;
    private Customer kimberliBauer;
    private Customer davidPalmer;
    private Customer michelleDessler;

    @Before
    public void setUp() throws Exception {
        jackBauer = repository.save(new Customer("Jack", "Bauer", 30));
        chloeOBrian = repository.save(new Customer("Chloe", "O'Brian", 15));
        kimberliBauer = repository.save(new Customer("Kimberli", "Bauer", 10));
        davidPalmer = repository.save(new Customer("David", "Palmer", 20));
        michelleDessler = repository.save(new Customer("Michelle", "Dessler", 11));
    }

    @Test
    public void testFindOne() throws Exception {
        long id = kimberliBauer.getId();
        Assert.assertThat(kimberliBauer, Matchers.is(repository.findOne(id)));
    }

    @Test
    public void testFindAll() throws Exception {
        Iterable<Customer> customers = repository.findAll();
        Assert.assertThat(customers, Matchers.containsInAnyOrder(jackBauer, chloeOBrian, kimberliBauer, davidPalmer, michelleDessler));
    }

    @Test
    public void testFindByLastName() throws Exception {
        List<Customer> customers = repository.findByLastName("Bauer");
        Assert.assertThat(customers, Matchers.containsInAnyOrder(kimberliBauer, jackBauer));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNullConstraint() throws Exception {
        repository.save(new Customer("Bruce", null, 42));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testLengthConstraint() throws Exception {
        repository.save(new Customer("Bruce", "Lee", 42));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testMinConstraint() throws Exception {
        repository.save(new Customer("Bruce", "Willis", -1));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testUniqueDataIntegrity() throws Exception {
        repository.save(new Customer("Bruce", "Willis", 42));
        repository.save(new Customer("Bruce", "Willis", 42));
    }

    @Test
    public void testFindByFirstNameAndLastName() throws Exception {
        Customer customer = repository.findByFirstNameAndLastName("Kimberli", "Bauer");
        Assert.assertThat(customer, Matchers.is(kimberliBauer));
    }

    @Test
    public void testFindByAgeBetween() throws Exception {
        List<Customer> customers = repository.findByAgeBetween(18, 21);
        Assert.assertThat(customers, Matchers.containsInAnyOrder(davidPalmer));
    }

    @Test
    public void testFindByFirstNameLikeOrLastNameLike() throws Exception {
        List<Customer> customers = repository.findByFirstNameLikeOrLastNameLike("Chloe", "Palmer");
        Assert.assertThat(customers, Matchers.containsInAnyOrder(chloeOBrian, davidPalmer));
    }
}