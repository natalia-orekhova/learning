package ru.tata.spring.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "supply_position", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"article"})
})
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class SupplyPosition extends AbstractPersistable<Long>{
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "supply_id")
    @JsonSerialize(as=AbstractPersistable.class)
    private Supply supply;
    @Column(name = "article", nullable = false, unique = true)
    private String article;
    @Column(name = "declared_amount")
    @Min(0)
    private int declaredAmount;
    @Column(name = "accepted_amount")
    @Min(0)
    private int acceptedAmount;

    public SupplyPosition() {

    }

    public SupplyPosition(String article, int declaredAmount, int acceptedAmount) {
        this.article = article;
        this.declaredAmount = declaredAmount;
        this.acceptedAmount = acceptedAmount;
    }

    public SupplyPosition(Supply supply, String article, int declaredAmount) {
        this.supply = supply;
        this.article = article;
        this.declaredAmount = declaredAmount;
        this.acceptedAmount = 0;
        supply.getPositions().add(this);
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getDeclaredAmount() {
        return declaredAmount;
    }

    public void setDeclaredAmount(int declaredAmount) {
        this.declaredAmount = declaredAmount;
    }

    public int getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(int acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("supply", supply.getName())
                .add("article", article)
                .add("declaredAmount", declaredAmount)
                .add("acceptedAmount", acceptedAmount)
                .toString();
    }
}
