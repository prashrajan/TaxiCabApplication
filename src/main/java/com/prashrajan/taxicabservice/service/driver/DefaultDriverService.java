package com.prashrajan.taxicabservice.service.driver;

import com.prashrajan.taxicabservice.dataaccessobject.DriverRepository;
import com.prashrajan.taxicabservice.domainobject.CarDO;
import com.prashrajan.taxicabservice.domainobject.DriverDO;
import com.prashrajan.taxicabservice.domainvalue.CarOnlineStatus;
import com.prashrajan.taxicabservice.domainvalue.GeoCoordinate;
import com.prashrajan.taxicabservice.domainvalue.OnlineStatus;
import com.prashrajan.taxicabservice.exception.CarAlreadyInUseException;
import com.prashrajan.taxicabservice.exception.ConstraintsViolationException;
import com.prashrajan.taxicabservice.exception.EntityNotFoundException;
import com.prashrajan.taxicabservice.service.car.CarService;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;

    private final CarService carService;


    public DefaultDriverService(final DriverRepository driverRepository, final CarService carService)
    {
        this.driverRepository = driverRepository;
        this.carService = carService;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * 
     * @param driverId
     * @param onlineStatus
     * @param selectedCar
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateOnlineStatus(long driverId, OnlineStatus onlineStatus, Long selectedCar) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setOnlineStatus(onlineStatus);
        driverDO.setSelectedCar(selectedCar);

    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = driverRepository.findOne(driverId);
        if (driverDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + driverId);
        }
        return driverDO;
    }


    @Override
    public void selectCar(long driverId, long carId) throws CarAlreadyInUseException, EntityNotFoundException
    {

        DriverDO driverDO = driverRepository.findOne(driverId);
        if (driverDO == null)
        {
            throw new EntityNotFoundException("Could not find Driver with id: " + driverId);
        }

        if (driverDO.getOnlineStatus() == OnlineStatus.ONLINE)
        {
            throw new EntityNotFoundException("Driver has allready selecetd a car!!!: " + driverId);
        }

        CarDO carDO = carService.find(carId);
        if (carDO == null)
        {
            throw new EntityNotFoundException("Could not find the car with id: " + carId);
        }

        if (carDO.getCarOnlineStatus().equals(CarOnlineStatus.ONLINE))
        {
            throw new CarAlreadyInUseException("Car is Already in use : " + carId + " please select a different car!!!");
        }

        updateOnlineStatus(driverId, OnlineStatus.ONLINE, carId);
        carService.updateCarOnlineStatus(carId, CarOnlineStatus.ONLINE);

    }


    @Override
    public void deSelectCar(long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = driverRepository.findOne(driverId);
        if (driverDO == null)
        {
            throw new EntityNotFoundException("Could not find Driver with id: " + driverId);
        }

        if (driverDO.getOnlineStatus() == OnlineStatus.OFFLINE)
        {
            throw new EntityNotFoundException("This is a OFFLINE Driver !!! :" + driverId);
        }

        CarDO carDO = carService.find(driverDO.getSelectedCar());

        if (carDO == null)
        {
            throw new EntityNotFoundException("Could not find Driver with id: " + driverDO.getSelectedCar());
        }

        updateOnlineStatus(driverId, OnlineStatus.OFFLINE, 0L);
        carService.updateCarOnlineStatus(carDO.getId(), CarOnlineStatus.OFFLINE);

    }

}
