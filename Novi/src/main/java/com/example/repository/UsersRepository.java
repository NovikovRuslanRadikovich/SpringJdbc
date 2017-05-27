package com.example.repository;

import com.example.entities.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UsersRepository extends CrudRepository<User,Long> , JpaSpecificationExecutor<User> {

    User findOne(Long ID);

    User save(User user);

    List<User> findAll();
}
