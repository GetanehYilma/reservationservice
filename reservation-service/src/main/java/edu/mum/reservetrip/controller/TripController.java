package edu.mum.reservetrip.controller;


import edu.mum.reservetrip.model.*;
import edu.mum.reservetrip.service.ReservationTableService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel")
public class TripController {

    @Autowired
    private ReservationTableService reservationTableService;

    @GetMapping("/")
    public String helloTrip(){
        return "hello from Trip controller";
    }

    @PostMapping("/reserve/{userId}")
    public Trip reserveTrip(@PathVariable long userId, @RequestBody Trip trip) throws JSONException {

        ReservationTable rt = reservationTableService.reservationTableMapper(userId, trip);

        //Reduce available seat
        rt.setNumberOfAvailableSeats(trip.getNumberOfAvailableSeats() - trip.getRequestedReserveSeat());
        //save the reservation
        ReservationTable rtsaved = reservationTableService.saveReservation(rt);
        //get the trip from saved reservation
        Trip savedTrip = reservationTableService.tripMapper(rtsaved);
        //publish the reservation
        reservationTableService.publish(savedTrip);

        return savedTrip;
    }

    @PostMapping("/cancel/{userId}")
    public Trip cancelTrip(@PathVariable long userId, @RequestBody Trip trip) throws JSONException {

        ReservationTable reservation = reservationTableService.getReservationData(userId, trip.getId());
        reservation.setNumberOfAvailableSeats(reservation.getNumberOfAvailableSeats() + reservation.getNoOfReservedSeats());
        reservation.setCanceled(true);

        ReservationTable cancelledReservation = reservationTableService.saveReservation(reservation);
        Trip cancelledTrip = reservationTableService.tripMapper(cancelledReservation);

        reservationTableService.publish(cancelledTrip);

        return cancelledTrip;

    }
}
