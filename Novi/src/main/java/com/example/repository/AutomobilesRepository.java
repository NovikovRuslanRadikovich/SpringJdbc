package com.example.repository;

import com.example.entities.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AutomobilesRepository extends CrudRepository<Automobile,Long> , JpaSpecificationExecutor<Automobile> {

    List<Automobile> findAll();

    void delete(Long ID);

    Automobile save(Automobile automobile);

    void delete(Automobile automobile);

}
