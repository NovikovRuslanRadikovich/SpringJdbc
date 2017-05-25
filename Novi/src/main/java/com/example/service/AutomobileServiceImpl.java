package com.example.service;

import com.example.entities.Automobile;
import com.example.repository.AutomobilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
@Transactional
public class AutomobileServiceImpl implements AutomobilesService  {

    @Autowired
    AutomobilesRepository automobilesRepository;

    @PostConstruct
    public void generateTestData(){
        save(new Automobile("bmw", 1L, 1L, 1L, 1L));
        save(new Automobile("mercedes", 1L, 1L, 1L, 1L));
        save(new Automobile("audi", 1L, 1L, 1L, 1L));
        save(new Automobile("maybach", 1L, 1L, 1L, 1L));
    }

    @Override
    public List<Automobile> findAll() {return automobilesRepository.findAll();}

    @Override
    public void delete(Long ID) {automobilesRepository.delete(ID);}

    @Override
    public void save(Automobile automobile) {automobilesRepository.save(automobile);}

    @Override
    public void delete(Automobile automobile) {automobilesRepository.delete(automobile);}


    public Automobile findByModel(String autoName) {return automobilesRepository.findByModel(autoName);}


}
