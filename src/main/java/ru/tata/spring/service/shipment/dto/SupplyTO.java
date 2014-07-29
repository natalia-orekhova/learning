package ru.tata.spring.service.shipment.dto;

import com.google.common.collect.Lists;

import java.util.Collection;

public class SupplyTO {
    private String name;

    private Collection<SupplyPositionTo> positions;

    public SupplyTO(String name, SupplyPositionTo... positions) {
        this.name = name;
        this.positions = Lists.newArrayList(positions);
        //TODO
    }

    public String getName() {
        return name;
    }

    public Collection<SupplyPositionTo> getPositions() {
        return positions;
    }
}
