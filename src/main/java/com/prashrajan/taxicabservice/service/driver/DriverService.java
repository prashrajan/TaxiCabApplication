package com.prashrajan.taxicabservice.service.driver;

import com.prashrajan.taxicabservice.domainobject.DriverDO;
import com.prashrajan.taxicabservice.domainvalue.OnlineStatus;
import com.prashrajan.taxicabservice.exception.ConstraintsViolationException;
import com.prashrajan.taxicabservice.exception.EntityNotFoundException;
import com.prashrajan.taxicabservice.exception.CarAlreadyInUseException;
import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;


    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;


    void delete(Long driverId) throws EntityNotFoundException;


    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;


    List<DriverDO> find(OnlineStatus onlineStatus);


    void selectCar(long driverId, long carId) throws CarAlreadyInUseException, EntityNotFoundException;


    void deSelectCar(long driverId) throws EntityNotFoundException;


    void updateOnlineStatus(long driverId, OnlineStatus onlineStatus, Long carSelected) throws EntityNotFoundException;

}
