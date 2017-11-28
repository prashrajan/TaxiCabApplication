package com.prashrajan.taxicabservice.service.car;

import java.util.List;

import com.prashrajan.taxicabservice.domainobject.CarDO;
import com.prashrajan.taxicabservice.domainvalue.CarOnlineStatus;
import com.prashrajan.taxicabservice.exception.ConstraintsViolationException;
import com.prashrajan.taxicabservice.exception.EntityNotFoundException;

/**
 * @author prajan
 */
public interface CarService
{
    CarDO find(Long carId) throws EntityNotFoundException;


    CarDO create(CarDO carDO) throws ConstraintsViolationException;


    void delete(Long carId) throws EntityNotFoundException;


    List<CarDO> find(CarOnlineStatus carOnlineStatus);


    void updateCarOnlineStatus(long carId, CarOnlineStatus carOnlineStatus) throws EntityNotFoundException;
}
