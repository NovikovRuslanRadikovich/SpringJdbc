package com.example.repository;

import com.example.entities.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ReservationsRepository extends CrudRepository<Reservation,Long> {

    Reservation findOne(Long ID);

    void delete(Long ID);

    List<Reservation> findAll();

    Reservation save(Reservation reservation);
}
