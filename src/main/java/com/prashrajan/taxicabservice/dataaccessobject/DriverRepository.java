package com.prashrajan.taxicabservice.dataaccessobject;

import com.prashrajan.taxicabservice.domainobject.DriverDO;
import com.prashrajan.taxicabservice.domainvalue.OnlineStatus;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
}
