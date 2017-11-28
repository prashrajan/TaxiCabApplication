package com.prashrajan.taxicabservice.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.prashrajan.taxicabservice.domainvalue.CarManufacturer;
import com.prashrajan.taxicabservice.domainvalue.CarOnlineStatus;
import com.prashrajan.taxicabservice.domainvalue.CarType;
import com.prashrajan.taxicabservice.domainvalue.EngineType;

/**
 * @author prajan
 *TODO: do docs
 */

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_license_plate", columnNames = {"licensePlate"}))
public class CarDO
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "modelName can not be null!")
    private String modelName;

    @Column(nullable = false)
    @NotNull(message = "license_plate can not be null!")
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType carType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineType engineType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarManufacturer carManufacturer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarOnlineStatus carOnlineStatus;
    
    @Column(nullable = false)
    private Boolean deleted = false;

    private int rating;

    private int seatCount;
    
    public CarDO(String modelName, String licensePlate, CarType carType, EngineType engineType, int rating, int seatCount, CarManufacturer carManufacturer)
    {
        this.modelName = modelName;
        this.licensePlate = licensePlate;
        this.carType = carType;
        this.engineType = engineType;
        this.rating = rating;
        this.seatCount = seatCount;
        this.carManufacturer = carManufacturer;
        this.carOnlineStatus = CarOnlineStatus.OFFLINE;
    }
    
    public CarDO(){
    	
    }

    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getModelName()
    {
        return modelName;
    }


    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public CarType getCarType()
    {
        return carType;
    }


    public void setCarType(CarType carType)
    {
        this.carType = carType;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public void setEngineType(EngineType engineType)
    {
        this.engineType = engineType;
    }


    public CarManufacturer getCarManufacturer()
    {
        return carManufacturer;
    }


    public void setCarManufacturer(CarManufacturer carManufacturer)
    {
        this.carManufacturer = carManufacturer;
    }


    public int getRating()
    {
        return rating;
    }


    public void setRating(int rating)
    {
        this.rating = rating;
    }


    public int getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(int seatCount)
    {
        this.seatCount = seatCount;
    }


    public CarOnlineStatus getCarOnlineStatus()
    {
        return carOnlineStatus;
    }


    public void setCarOnlineStatus(CarOnlineStatus carOnlineStatus)
    {
        this.carOnlineStatus = carOnlineStatus;
    }

    public Boolean getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }

}
