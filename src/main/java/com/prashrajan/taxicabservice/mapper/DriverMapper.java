package com.prashrajan.taxicabservice.mapper;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.prashrajan.taxicabservice.datatransferobject.DriverDTO;
import com.prashrajan.taxicabservice.domainobject.CarDO;
import com.prashrajan.taxicabservice.domainobject.DriverDO;
import com.prashrajan.taxicabservice.domainvalue.CarManufacturer;
import com.prashrajan.taxicabservice.domainvalue.CarType;
import com.prashrajan.taxicabservice.domainvalue.GeoCoordinate;
import com.prashrajan.taxicabservice.exception.EntityNotFoundException;
import com.prashrajan.taxicabservice.service.car.CarService;

public class DriverMapper
{

    @Autowired

    private static final Logger LOGGER = Logger.getLogger(DriverMapper.class.getName());


    public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
        return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
            .setId(driverDO.getId())
            .setPassword(driverDO.getPassword())
            .setUsername(driverDO.getUsername());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers.stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }


    public static List<DriverDTO> makeDriverWithCarTypeDTOList(Collection<DriverDO> drivers, CarType carType, CarService carService)
    {
        return drivers.stream()
            .filter(driver -> carType.toString().equals(findCarTypeForDriver(driver, carService)))
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());

    }


    public static List<DriverDTO> makeDriverWithCarManufacturerDTOList(Collection<DriverDO> drivers, CarManufacturer carManufacturer, CarService carService)
    {
        return drivers.stream()
            .filter(driver -> carManufacturer.toString().equals(findCarManufacturerForDriver(driver, carService)))
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }


    public static List<DriverDTO> makeDriverWithCarRatingDTOList(List<DriverDO> drivers, int rating, CarService carService)
    {
        return drivers.stream()
            .filter(driver -> rating == findCarRatingForDriver(driver, carService))
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }


    private static String findCarTypeForDriver(DriverDO driver, CarService carService)
    {
        try
        {
            CarDO carDO = carService.find(driver.getSelectedCar());
            return carDO.getCarType().toString();
        }
        catch (EntityNotFoundException e)
        {
            LOGGER.log(Level.INFO, "could not find car ", driver.getSelectedCar());
            return "";
        }

    }


    private static String findCarManufacturerForDriver(DriverDO driver, CarService carService)
    {
        try
        {
            CarDO carDO = carService.find(driver.getSelectedCar());
            return carDO.getCarManufacturer().toString();
        }
        catch (EntityNotFoundException e)
        {
            LOGGER.log(Level.INFO, "could not find car ", driver.getSelectedCar());
            return "";
        }

    }


    private static int findCarRatingForDriver(DriverDO driver, CarService carService)
    {
        try
        {
            CarDO carDO = carService.find(driver.getSelectedCar());
            return carDO.getRating();
        }
        catch (EntityNotFoundException e)
        {
            LOGGER.log(Level.INFO, "could not find car ", driver.getSelectedCar());
            return 0;
        }
    }

}
