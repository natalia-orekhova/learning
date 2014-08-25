package ru.tata.spring.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Item extends AbstractPersistable<Long> {
    @Column(name = "is_reserved")
    private boolean isReserved;
    @Column(name = "article")
    private String article;

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getArticle() {
        return article;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + getId() +
                ",isReserved=" + isReserved +
                ", article='" + article + '\'' +
                '}';
    }
}
