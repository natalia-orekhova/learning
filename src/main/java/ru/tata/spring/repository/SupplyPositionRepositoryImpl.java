package ru.tata.spring.repository;

import javax.annotation.Nonnull;
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
}
