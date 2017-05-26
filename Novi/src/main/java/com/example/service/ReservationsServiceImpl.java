package com.example.service;

import com.example.entities.Reservation;
import com.example.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ReservationsServiceImpl implements ReservationsService {

    @Autowired
    ReservationsRepository reservationsRepository;

    @PostConstruct
    public void generateData() {
          save(new Reservation("sidney","123","bmw","2005/11/16","2006/03/12"));
          save(new Reservation("evgeny","123","bmw","2004/11/16","2009/03/12"));
          save(new Reservation("ruslan","123","bmw","2005/11/16","2006/03/12"));
          save(new Reservation("vladimir","123","bmw","2005/11/16","2006/03/12"));
    }

    @Override
    public Reservation findOne(Long ID) {return reservationsRepository.findOne(ID);}

    @Override
    public void delete(Long ID) {reservationsRepository.delete(ID);}

    @Override
    public List<Reservation> findAll() {
       return reservationsRepository.findAll();
    }

    @Override
    public void delete(Reservation reservation) {reservationsRepository.delete(reservation);}

    @Override
    public void save(Reservation reservation) {reservationsRepository.save(reservation);}

    @Override
    public void update(Reservation reservation) {reservationsRepository.save(reservation);}
}
