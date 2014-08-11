package ru.tata.spring.repository;

import ru.tata.spring.model.SupplyPosition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        return (SupplyPosition) em.createQuery("" +
                "select position " +
                "from Supply s " +
                "inner join s.positions position " +
                "where s.name = :supply " +
                "  and position.article = :article " +
                "")
                .setParameter("supply", supplyName)
                .setParameter("article", article)
                .getSingleResult();
    }
}
