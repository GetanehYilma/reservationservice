package edu.mum.reservetrip.repository;

import edu.mum.reservetrip.model.ReservationTable;
import edu.mum.reservetrip.model.ReservationUserTripPK;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationTableRepository extends CassandraRepository<ReservationTable, Long> {

    @AllowFiltering
    ReservationTable findByUserIdAndTripId(long userId, long tripId);
}
