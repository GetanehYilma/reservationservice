package edu.mum.reservetrip.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Data
public class Reservation {

    private long id;
    private int noOfReservedSeats;
    private boolean canceled = false;
    private User reservedBy;
}
