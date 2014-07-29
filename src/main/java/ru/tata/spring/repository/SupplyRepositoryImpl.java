package ru.tata.spring.repository;

import ru.tata.spring.model.State;
import ru.tata.spring.model.Supply;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SupplyRepositoryImpl implements SupplyRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Supply> getNoClosed() {
        return em
            .createQuery("" +
                    "select s " +
                    "from Supply s " +
                    "where s.state <> :finish",
                    Supply.class
            )
            .setParameter("finish", State.CLOSED)
            .getResultList();
    }

}
