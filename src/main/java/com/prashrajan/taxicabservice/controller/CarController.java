package com.prashrajan.taxicabservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prashrajan.taxicabservice.mapper.CarMapper;
import com.prashrajan.taxicabservice.datatransferobject.CarDTO;
import com.prashrajan.taxicabservice.domainobject.CarDO;
import com.prashrajan.taxicabservice.domainvalue.CarOnlineStatus;
import com.prashrajan.taxicabservice.exception.ConstraintsViolationException;
import com.prashrajan.taxicabservice.exception.EntityNotFoundException;
import com.prashrajan.taxicabservice.service.car.CarService;

/**
 * @author prashant rajan
 *
 * All operations with a car will be routed by this controller.
 * <p/>
 */

@RestController
@RequestMapping("v1/cars")
public class CarController
{
    private final CarService carService;


    @Autowired
    public CarController(final CarService carService)
    {
        this.carService = carService;
    }


    @GetMapping("/{carId}")
    public CarDTO getCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));
    }


    @DeleteMapping("/{carId}")
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }
    
    @GetMapping
    public List<CarDTO> findCars(@RequestParam CarOnlineStatus carOnlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return CarMapper.makeCarDTOList(carService.find(carOnlineStatus));
    }

}
