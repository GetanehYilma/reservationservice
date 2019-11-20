package edu.mum.reservetrip.model;


import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

//@PrimaryKeyClass
public class ReservationUserTripPK {


//    @PrimaryKeyColumn(name = "userId", ordinal = 0, type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
//    private long userId;
//
//    @PrimaryKeyColumn(name = "trip_id", ordinal = 2, type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
//    private long tripId;
//
//    @PrimaryKeyColumn(name = "driver_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
//    private long driverId;
//
//    @PrimaryKeyColumn(name = "reservation_id", ordinal = 3, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
//    private UUID reservationId;
}
