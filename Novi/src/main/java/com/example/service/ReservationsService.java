package com.example.service;

import com.example.entities.Reservation;

import java.util.List;


public interface ReservationsService {
    Reservation findOne(Long ID);

    void delete(Long ID);

    List<Reservation> findAll();

    void delete(Reservation reservation);

    void save(Reservation reservation);

    void update(Reservation reservation);
}
