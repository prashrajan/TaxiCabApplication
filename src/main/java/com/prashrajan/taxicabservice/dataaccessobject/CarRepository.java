package com.prashrajan.taxicabservice.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prashrajan.taxicabservice.domainobject.CarDO;
import com.prashrajan.taxicabservice.domainvalue.CarOnlineStatus;

public interface CarRepository extends CrudRepository<CarDO, Long>
{
    List<CarDO> findByCarOnlineStatus(CarOnlineStatus carOnlineStatus);
}
