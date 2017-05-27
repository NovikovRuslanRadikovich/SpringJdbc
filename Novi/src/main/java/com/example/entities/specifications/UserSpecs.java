package com.example.entities.specifications;


import com.example.entities.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class UserSpecs  {

    public static Specification<User> checkParams( String username,  String password) {
        return new Specification<User>() {

            List<Predicate> predicates = new ArrayList<>();

            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                if(username != null) {
                    predicates.add(cb.equal(root.get("username"), username));
                }
                if(password != null) {
                    predicates.add(cb.equal(root.get("password"), password));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }

        };
    }

    public static Specification<User> checkParams2(String username, String telephone) {
        return new Specification<User>() {

            List<Predicate> predicates = new ArrayList<>();

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {


                if(username != null) {
                    predicates.add(cb.equal(root.get("username"), username));
                }
                if(telephone != null) {
                    predicates.add(cb.equal(root.get("telephone"), telephone));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }

        };
    }
 }
