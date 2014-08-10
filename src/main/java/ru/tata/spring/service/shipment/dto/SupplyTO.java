package ru.tata.spring.service.shipment.dto;

import com.google.common.collect.Lists;

import java.util.Collection;

public class SupplyTO {
    private String name;
    private Collection<SupplyPositionTO> positions;

    public SupplyTO(String name, SupplyPositionTO... positions) {
        this.name = name;
        this.positions = Lists.newArrayList(positions);
    }

    public String getName() {
        return name;
    }

    public Collection<SupplyPositionTO> getPositions() {
        return positions;
    }

    //todo add another fields?
}
