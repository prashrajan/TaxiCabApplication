package com.prashrajan.taxicabservice.service.car;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prashrajan.taxicabservice.dataaccessobject.CarRepository;
import com.prashrajan.taxicabservice.domainobject.CarDO;
import com.prashrajan.taxicabservice.domainvalue.CarOnlineStatus;
import com.prashrajan.taxicabservice.exception.ConstraintsViolationException;
import com.prashrajan.taxicabservice.exception.EntityNotFoundException;
import com.prashrajan.taxicabservice.service.driver.DefaultDriverService;

/**
 * @author prajan
 *
 */

@Service
public class DefaultCarService implements CarService
{   
    
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);
    
    private final CarRepository carRepository;
    
    public DefaultCarService(final CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }
    
    /**
     * Selects a car by id.
     *
     * @param carId
     * @return found driver
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCar(carId);
    }
    
    /**
     * Creates a new Car.
     *
     * @param carDO
     * @return
     * @throws ConstraintsViolationException if a car already exists with the given licensePlate, ... .
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
        CarDO car;
        try
        {
            car = carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }
    
    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = findCar(carId);
        carDO.setDeleted(true);
    }
    
    /**
     * 
     * @param driverId
     * @param onlineStatus
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateCarOnlineStatus (long carId, CarOnlineStatus carOnlineStatus) throws EntityNotFoundException
    {
        CarDO carDO  = find(carId);
        carDO.setCarOnlineStatus(carOnlineStatus);
    }

    @Override
    public List<CarDO> find(CarOnlineStatus carOnlineStatus)
    {
        return carRepository.findByCarOnlineStatus(carOnlineStatus);
    }
    
    private CarDO findCar(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = carRepository.findOne(carId);
        if (carDO == null)
        {
            throw new EntityNotFoundException("Could not find car entity with id: " + carId);
        }
        return carDO;
    }

}
