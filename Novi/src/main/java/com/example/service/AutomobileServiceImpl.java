package com.example.service;

import com.example.entities.Automobile;
import com.example.entities.specifications.AutoSpecs;
import com.example.entities.specifications.UserSpecs;
import com.example.repository.AutomobilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
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
        save(new Automobile("bmw", "1", "1", "1", "1"));
        save(new Automobile("mercedes", "1", "1","1" , "1"));
        save(new Automobile("audi", "1", "1","1" , "1"));
        save(new Automobile("maybach", "1", "1","1" , "1"));
    }

    @Override
    public List<Automobile> findAll() {return automobilesRepository.findAll();}

    @Override
    public void update(Automobile automobile) {
        automobilesRepository.save(automobile);
    }

    @Override
    public void delete(Long ID) {automobilesRepository.delete(ID);}

    @Override
    public void save(Automobile automobile) {automobilesRepository.save(automobile);}

    @Override
    public void delete(Automobile automobile) {automobilesRepository.delete(automobile);}

    @Override
    public Automobile findFromModel(String autoModel) {
        return automobilesRepository.findOne(Specifications.where(AutoSpecs.checkParam(autoModel)));
    }

}
