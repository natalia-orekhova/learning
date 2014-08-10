package ru.tata.spring.service.shipment.dto;

public class SupplyPositionTO {
    private String article;
    private int declaredAmount;

    public SupplyPositionTO(String article, int declaredAmount) {
        this.article = article;
        this.declaredAmount = declaredAmount;
    }

    public String getArticle() {

        return article;
    }

    public int getDeclaredAmount() {
        return declaredAmount;
    }

    //todo add another fields?
}
