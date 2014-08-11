package ru.tata.spring.repository;

import ru.tata.spring.model.SupplyPosition;
import ru.tata.spring.model.SupplyState;
import ru.tata.spring.model.Supply;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
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
            .setParameter("finish", SupplyState.CLOSED)
            .getResultList();
    }

    public boolean existByName(@Nonnull String name) {
        return em
            .createQuery("" +
                    "select " +
                    "   case " +
                    "       when (count (s) = 0) then false " +
                    "       else true " +
                    "   end " +
                    "from Supply s " +
                    "where s.name = :name",
                Boolean.class
            ).setParameter("name", name)
            .getSingleResult();
    }
}
