package ru.kpfu.itis.NovikovRuslan.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.NovikovRuslan.entities.City;
import ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.CitiesDaoHibernateImpl;

import java.util.List;


@Service
public class CitiesService {
    @Autowired
    CitiesDaoHibernateImpl citiesDao;

    public List<City> getAllCities(){return citiesDao.getAllCities();}

    public void saveCity(City city) {citiesDao.saveCity(city);}

    public void deleteCity(Long id) {citiesDao.deleteCity(id);}

    public City getCity(Long id) {return citiesDao.getCity(id);}

    public City getCity(String polyclinic_city) {
        return citiesDao.getCity(polyclinic_city);
    }
}
