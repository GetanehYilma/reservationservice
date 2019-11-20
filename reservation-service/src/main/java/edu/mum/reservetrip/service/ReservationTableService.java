package edu.mum.reservetrip.service;

import edu.mum.reservetrip.model.ReservationTable;
import edu.mum.reservetrip.model.Trip;

public interface ReservationTableService {

    ReservationTable saveReservation(ReservationTable reservationTable);
    ReservationTable reservationTableMapper(long userId, Trip trip);
    Trip tripMapper(ReservationTable rt);
    ReservationTable getReservationData(long userId, long tripId);
    void publish(Trip trip);
}
