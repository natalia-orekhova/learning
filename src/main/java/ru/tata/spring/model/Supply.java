package ru.tata.spring.model;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "supply", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Supply extends AbstractPersistable<Long>{
    @Column(name = "name", unique = true, nullable = false)
    @NotNull
    private String name;
    @Column(name = "state", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private SupplyState state;
    @Column(name = "created")
    private Date created;
    @Column(name = "arrived")
    private Date arrived;
    @Column(name = "closed")
    private Date closed;
    @NotNull
    @OneToMany(mappedBy = "supply")
    private List<SupplyPosition> positions = Lists.newArrayList();

    public Supply() {
    }

    public Supply(String name, SupplyState state, Date created, Date arrived, Date closed, SupplyPosition... positions) {
        this.name = name;
        this.state = state;
        this.created = created;
        this.arrived = arrived;
        this.closed = closed;
        this.positions = Lists.newArrayList(positions);
    }

    public Supply(String name) {
        setName(name);
        setState(SupplyState.CREATED);
        setCreated(new Date());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SupplyState getState() {
        return state;
    }

    public void setState(SupplyState state) {
        this.state = state;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getArrived() {
        return arrived;
    }

    public void setArrived(Date arrived) {
        this.arrived = arrived;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public List<SupplyPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<SupplyPosition> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("name", name)
                .add("state", state)
                .add("created", created)
                .add("arrived", arrived)
                .add("closed", closed)
                .add("positions", positions)
                .toString();
    }
}
