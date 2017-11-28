package com.prashrajan.taxicabservice.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.prashrajan.taxicabservice.domainvalue.CarManufacturer;
import com.prashrajan.taxicabservice.domainvalue.CarType;
import com.prashrajan.taxicabservice.domainvalue.EngineType;

/**
 * @author prajan
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "License Plate can not be null!")
    private String licensePlate;

    @NotNull(message = "Model Name can not be null!")
    private String modelName;

    @NotNull(message = "Car type can not be null!")
    private CarType carType;

    @NotNull(message = "Engine Type can not be null!")
    private EngineType engineType;

    @NotNull(message = "Car Manufacturer can not be null!")
    private CarManufacturer carManufacturer;

    private int rating;

    private int seatCount;


    private CarDTO()
    {}


    private CarDTO(Long id, String licensePlate, String modelName, CarType carType, EngineType engineType, CarManufacturer carManufacturer, int rating, int seatCount)
    {
        this.id = id;
        this.licensePlate = licensePlate;
        this.modelName = modelName;
        this.carType = carType;
        this.engineType = engineType;
        this.carManufacturer = carManufacturer;
        this.rating = rating;
        this.seatCount = seatCount;
    }


    public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    public Long getId()
    {
        return id;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public String getModelName()
    {
        return modelName;
    }


    public CarType getCarType()
    {
        return carType;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public CarManufacturer getCarManufacturer()
    {
        return carManufacturer;
    }


    public int getRating()
    {
        return rating;
    }


    public int getSeatCount()
    {
        return seatCount;
    }

    public static class CarDTOBuilder
    {
        private Long id;
        private String licensePlate;
        private String modelName;
        private CarType carType;
        private EngineType engineType;
        private CarManufacturer carManufacturer;
        private int rating;
        private int seatCount;


        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }


        public CarDTOBuilder setModelName(String modelName)
        {
            this.modelName = modelName;
            return this;
        }


        public CarDTOBuilder setCarType(CarType carType)
        {
            this.carType = carType;
            return this;
        }


        public CarDTOBuilder setEngineType(EngineType engineType)
        {
            this.engineType = engineType;
            return this;
        }


        public CarDTOBuilder setCarManufacturer(CarManufacturer carManufacturer)
        {
            this.carManufacturer = carManufacturer;
            return this;
        }


        public CarDTOBuilder setRating(int rating)
        {
            this.rating = rating;
            return this;
        }


        public CarDTOBuilder setSeatCount(int seatCount)
        {
            this.seatCount = seatCount;
            return this;
        }


        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate, modelName, carType, engineType, carManufacturer, rating, seatCount);
        }

    }

}
