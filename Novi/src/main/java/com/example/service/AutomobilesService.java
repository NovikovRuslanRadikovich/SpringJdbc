package com.example.service;

import com.example.entities.Automobile;


import java.util.List;


public interface AutomobilesService {

    List<Automobile> findAll();

    void update(Automobile automobile);

    void delete(Long ID);

    void save(Automobile automobile);

    void delete(Automobile automobile);

     Automobile findByModel(String autoModel);


}
