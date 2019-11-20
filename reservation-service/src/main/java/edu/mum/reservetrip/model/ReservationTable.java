package edu.mum.reservetrip.model;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("reservationtable")
public class ReservationTable {

//    @PrimaryKey
//    private ReservationUserTripPK primarykey;

//    @PrimaryKeyColumn(name = "userId", ordinal = 0, type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
//    private long userId;
//
//    @PrimaryKeyColumn(name = "trip_id", ordinal = 2, type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
    private long tripId;
//
//    @PrimaryKeyColumn(name = "driver_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
//    private long driverId;

//    @PrimaryKeyColumn(name = "reservation_id", ordinal = 3, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
//    private UUID reservationId;
    @PrimaryKeyColumn(name = "reservation_id", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
    private long reservationId;

    private int noOfReservedSeats;
    private boolean canceled = false;
    private long userId;
    private String userFirstName;
    private String userLastName;
    private String userUsername;
    private String userEmailAddress;
    private long driverId;
    private String driverFirstName;
    private String driverLastName;
    private String driverUsername;
    private String driverEmailAddress;

    private String pickupPlace;
    private String dropOffPlace;
    private LocalDateTime tripStartingTime;
    private LocalDateTime tripEndTime;
    private boolean isRoundTrip;
    private Integer numberOfAvailableSeats;
    private int requestedReserveSeat;
    private Double tripPrice;
    private String tripDescription;
}
