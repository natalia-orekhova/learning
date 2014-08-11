package ru.tata.spring.service.shipment.impl;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tata.spring.model.ProcessPositionType;
import ru.tata.spring.model.Supply;
import ru.tata.spring.model.SupplyPosition;
import ru.tata.spring.model.SupplyState;
import ru.tata.spring.repository.SupplyPositionRepository;
import ru.tata.spring.repository.SupplyRepository;
import ru.tata.spring.service.exception.BusinessException;
import ru.tata.spring.service.shipment.SupplyService;
import ru.tata.spring.service.shipment.dto.SupplyPositionTO;
import ru.tata.spring.service.shipment.dto.SupplyTO;

import javax.annotation.Nonnull;
import java.util.Date;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private SupplyPositionRepository supplyPositionRepository;

    @Override
    public void importSupply(@Nonnull SupplyTO to) throws BusinessException {
        Preconditions.checkNotNull(to, "Supply is not defined");
        if (supplyRepository.existByName(to.getName())) {
            throw new SupplyAlreadyExistException(to.getName());
        }
        Supply supply = supplyRepository.save(new Supply(to.getName()));
        for (SupplyPositionTO positionTO : to.getPositions()) {
            String article = positionTO.getArticle();
            checkArticleAlreadyExist(article);
            supplyPositionRepository.save(new SupplyPosition(supply, article, positionTO.getDeclaredAmount()));
        }
    }

    @Override
    public void arriveSupply(@Nonnull String name) throws BusinessException {
        Preconditions.checkNotNull(name, "Supply name is not defined");
        checkSupplyExist(name);
        Supply supply = supplyRepository.findByName(name);
        checkSupplyInCorrectState(supply, SupplyState.CREATED);
        supply.setArrived(new Date());
        supply.setState(SupplyState.ARRIVED);
    }

    @Override
    public void activateSupply(@Nonnull String name) throws BusinessException {
        Preconditions.checkNotNull(name, "Supply name is not defined");
        checkSupplyExist(name);
        Supply supply = supplyRepository.findByName(name);
        checkSupplyInCorrectState(supply, SupplyState.ARRIVED);
        supply.setState(SupplyState.PROCESSING);
    }

    @Override
    public void closeSupply(@Nonnull String name) throws BusinessException {
        Preconditions.checkNotNull(name, "Supply name is not defined");
        checkSupplyExist(name);
        Supply supply = supplyRepository.findByName(name);
        checkSupplyInCorrectState(supply, SupplyState.PROCESSING);
        supply.setState(SupplyState.CLOSED);
        supply.setClosed(new Date());
    }

    @Override
    public ProcessPositionType processSupplyPosition(@Nonnull String name, @Nonnull String article) throws BusinessException {
        Preconditions.checkNotNull(name, "Supply name is not defined");
        Preconditions.checkNotNull(name, "Position article is not defined");
        checkSupplyExist(name);
        checkSupplyInCorrectState(supplyRepository.findByName(name), SupplyState.PROCESSING);
        SupplyPosition sp = supplyPositionRepository.findByArticleAndSupplyName(name, article);
        if(sp == null){
            throw new SupplyPositionDoesNotExistException(article, name);
        }
        //noinspection ConstantConditions
        sp.setAcceptedAmount(sp.getAcceptedAmount() + 1);
        return sp.getAcceptedAmount() > sp.getDeclaredAmount() ? ProcessPositionType.SURPLUS : ProcessPositionType.NORMAL;
    }

    private void checkSupplyExist(String name) throws SupplyDoesNotExistException {
        if (supplyRepository.findByName(name) == null) {
            throw new SupplyDoesNotExistException(name);
        }
    }

    private void checkSupplyInCorrectState(Supply supply, SupplyState expectedState) throws SupplyWrongStateException {
        if (!expectedState.equals(supply.getState())) {
            throw new SupplyWrongStateException(supply.getName(), supply.getState(), expectedState);
        }
    }

    private void checkArticleAlreadyExist(String article) throws SupplyPositionAlreadyExistException {
        if (supplyPositionRepository.findByArticle(article) != null) {
            throw new SupplyPositionAlreadyExistException(article);
        }
    }
}
