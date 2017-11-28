package com.prashrajan.taxicabservice.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.prashrajan.taxicabservice.datatransferobject.CarDTO;
import com.prashrajan.taxicabservice.domainobject.CarDO;

public class CarMapper
{
    public static CarDO makeCarDO(CarDTO carDTO)
    {
        return new CarDO(carDTO.getModelName(), carDTO.getLicensePlate(), carDTO.getCarType(), carDTO.getEngineType(), carDTO.getRating(), carDTO.getSeatCount(), carDTO.getCarManufacturer());
    }


    public static CarDTO makeCarDTO(CarDO carDO)
    {
        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
            .setId(carDO.getId())
            .setLicensePlate(carDO.getLicensePlate())
            .setModelName(carDO.getModelName())
            .setCarType(carDO.getCarType())
            .setEngineType(carDO.getEngineType())
            .setRating(carDO.getRating())
            .setSeatCount(carDO.getSeatCount())
            .setCarManufacturer(carDO.getCarManufacturer());

       

        return carDTOBuilder.createCarDTO();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars)
    {
        return cars.stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
