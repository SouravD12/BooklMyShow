package com.example.lld.bookmyshow.Repositories;

import com.example.lld.bookmyshow.models.SeatInShow;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<SeatInShow, Long> {

    @Override
    SeatInShow save(SeatInShow entity);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    List<SeatInShow> findAllById(Iterable<Long> longs);
}
