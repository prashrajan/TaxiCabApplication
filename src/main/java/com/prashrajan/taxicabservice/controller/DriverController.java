package com.prashrajan.taxicabservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prashrajan.taxicabservice.mapper.DriverMapper;
import com.prashrajan.taxicabservice.datatransferobject.DriverDTO;
import com.prashrajan.taxicabservice.domainobject.DriverDO;
import com.prashrajan.taxicabservice.domainvalue.CarManufacturer;
import com.prashrajan.taxicabservice.domainvalue.CarType;
import com.prashrajan.taxicabservice.domainvalue.OnlineStatus;
import com.prashrajan.taxicabservice.exception.CarAlreadyInUseException;
import com.prashrajan.taxicabservice.exception.ConstraintsViolationException;
import com.prashrajan.taxicabservice.exception.EntityNotFoundException;
import com.prashrajan.taxicabservice.service.car.CarService;
import com.prashrajan.taxicabservice.service.driver.DriverService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;

    private final CarService carService;


    @Autowired
    public DriverController(final DriverService driverService, final CarService carService)
    {
        this.driverService = driverService;
        this.carService = carService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @PutMapping("/{driverId}/selectCar")
    public void selectCar(
        @Valid @PathVariable long driverId, @RequestParam long carId)
        throws EntityNotFoundException, CarAlreadyInUseException
    {
        driverService.selectCar(driverId, carId);
    }


    @PutMapping("/{driverId}/deSelectCar")
    public void deSelectCar(
        @Valid @PathVariable long driverId)
        throws EntityNotFoundException
    {
        driverService.deSelectCar(driverId);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }


    /**
     * 
     * 
     * @param carType
     * @return List of Drivers who are online and driving a specific carType for example Sedan, Convertible etc..
     * @throws EntityNotFoundException
     */
    @GetMapping("/filterByCarType")
    public List<DriverDTO> findDriversWithCarType(@RequestParam CarType carType)
        throws EntityNotFoundException
    {
        return DriverMapper.makeDriverWithCarTypeDTOList(driverService.find(OnlineStatus.ONLINE), carType, carService);
    }


    /**
     * 
     * 
     * @param carManufacturer
     * @return List of Drivers who are online and driving a specific car for example Audi,BMW etc..
     * @throws EntityNotFoundException
     */
    @GetMapping("/filterByCarManufacturer")
    public List<DriverDTO> findDriversWithCarManufacturer(@RequestParam CarManufacturer carManufacturer)
        throws EntityNotFoundException
    {
        return DriverMapper.makeDriverWithCarManufacturerDTOList(driverService.find(OnlineStatus.ONLINE), carManufacturer, carService);
    }


    /**
     * 
     * 
     * @param rating
     * @return List of Drivers who are online and driving a specific car with specific rating
     * @throws EntityNotFoundException
     */
    @GetMapping("/filterByRatings")
    public List<DriverDTO> findDriversWithCarRating(@RequestParam int rating)
        throws EntityNotFoundException
    {
        return DriverMapper.makeDriverWithCarRatingDTOList(driverService.find(OnlineStatus.ONLINE), rating, carService);
    }

}
