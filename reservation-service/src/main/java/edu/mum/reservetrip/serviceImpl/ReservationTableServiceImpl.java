package edu.mum.reservetrip.serviceImpl;

import edu.mum.reservetrip.model.Reservation;
import edu.mum.reservetrip.model.ReservationTable;
import edu.mum.reservetrip.model.Trip;
import edu.mum.reservetrip.model.User;
import edu.mum.reservetrip.repository.ReservationTableRepository;
import edu.mum.reservetrip.service.ReservationTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReservationTableServiceImpl implements ReservationTableService {

    @Autowired
    private ReservationTableRepository reservationTableRepository;

    @Override
    public ReservationTable saveReservation(ReservationTable reservationTable) {
        return reservationTableRepository.save(reservationTable);
    }

    @Override
    public ReservationTable reservationTableMapper(long userId, Trip trip) {

        ReservationTable rt = new ReservationTable();
        rt.setTripId(trip.getId());
        rt.setUserId(userId);
        //get and save driver
        rt.setDriverId(trip.getDriver().getId());
        rt.setDriverEmailAddress(trip.getDriver().getEmailAddress());
        rt.setDriverFirstName(trip.getDriver().getFirstName());
        rt.setDriverLastName(trip.getDriver().getLastName());
        rt.setDriverUsername(trip.getDriver().getUsername());
        //make Reservation
        rt.setNoOfReservedSeats(trip.getRequestedReserveSeat());
        //get the trip
        rt.setPickupPlace(trip.getPickupPlace());
        rt.setDropOffPlace(trip.getDropOffPlace());
        rt.setTripStartingTime(trip.getTripStartingTime());
        rt.setTripEndTime(trip.getTripEndTime());
        rt.setRoundTrip(trip.isRoundTrip());
        rt.setTripPrice(trip.getTripPrice());
        rt.setTripDescription(trip.getTripDescription());

        return rt;
    }

    @Autowired
    private KafkaTemplate<String, Trip> kafkaTemplate;

    @Value("${kafka.topic.reservation}")
    private String kafkaTopic;

    public void publish(Trip trip){
        kafkaTemplate.send(kafkaTopic, trip);
    }

    @Override
    public Trip tripMapper(ReservationTable rt) {
        Trip trip = new Trip();
        trip.setId(rt.getTripId());
        trip.setPickupPlace(rt.getPickupPlace());
        trip.setDropOffPlace(rt.getDropOffPlace());
        trip.setTripStartingTime(rt.getTripStartingTime());
        trip.setTripEndTime(rt.getTripEndTime());
        trip.setRoundTrip(rt.isRoundTrip());
        trip.setNumberOfAvailableSeats(rt.getNumberOfAvailableSeats());
        trip.setRequestedReserveSeat(rt.getRequestedReserveSeat());
        trip.setTripPrice(rt.getTripPrice());
        trip.setTripDescription(rt.getTripDescription());

        User driver = new User();
        driver.setId(rt.getDriverId());
        driver.setFirstName(rt.getDriverFirstName());
        driver.setLastName(rt.getDriverLastName());
        driver.setUsername(rt.getDriverUsername());
        driver.setEmailAddress(rt.getDriverEmailAddress());
        trip.setDriver(driver);

        Reservation reservation = new Reservation();
        reservation.setId(rt.getReservationId());
        reservation.setNoOfReservedSeats(rt.getNoOfReservedSeats());
        reservation.setCanceled(rt.isCanceled());

        User user = new User();
        user.setId(rt.getUserId());
        //hardcoded values used here for now
        user.setEmailAddress("xx@xx.xx");
        user.setUsername("hallo");
        user.setFirstName(rt.getUserFirstName());
        user.setLastName(rt.getUserLastName());
        reservation.setReservedBy(user);
       // reservation.setTrip(trip);

        trip.setReservation(reservation);

        return trip;
    }

    @Override
    public ReservationTable getReservationData(long userId, long tripId) {
        return reservationTableRepository.findByUserIdAndTripId(userId, tripId);
    }

}
