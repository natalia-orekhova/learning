package ru.tata.spring.repository;

import ru.tata.spring.model.SupplyPosition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SupplyPositionRepositoryImpl implements SupplyPositionRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean existByArticle(@Nonnull String article) {
        return em
                .createQuery("" +
                                "select " +
                                "   case " +
                                "       when (count (sp) = 0) then false " +
                                "       else true " +
                                "   end " +
                                "from SupplyPosition sp " +
                                "where sp.article = :article",
                        Boolean.class
                ).setParameter("article", article)
                .getSingleResult();
    }

    @Nullable
    @Override
    public SupplyPosition findByArticleAndSupplyName(String supplyName, String article) {
        return em.createQuery("" +
                "select position " +
                "from Supply s " +
                "inner join s.positions position " +
                "where s.name = :supply " +
                "  and position.article = :article " +
                "", SupplyPosition.class)
                .setParameter("supply", supplyName)
                .setParameter("article", article)
                .getSingleResult();
    }

    @Nonnull
    @Override
    public List<SupplyPosition> findBySupply(long id) {
        return em.createQuery("" +
                "select position " +
                "from SupplyPosition position " +
                "inner join position.supply supply " +
                "where supply.id = :supplyId " +
                "", SupplyPosition.class)
                .setParameter("supplyId", id)
                .getResultList();

    }

    @Nullable
    @Override
    public SupplyPosition findBySupplyIdAndArticle(long id, String article) {
        return em.createQuery("" +
                "select position " +
                "from SupplyPosition  position " +
                "inner join position.supply supply " +
                "where supply.id = :supplyId " +
                "  and position.article = :article " +
                "", SupplyPosition.class)
                .setParameter("supplyId", id)
                .setParameter("article", article)
                .getSingleResult();
    }
}
