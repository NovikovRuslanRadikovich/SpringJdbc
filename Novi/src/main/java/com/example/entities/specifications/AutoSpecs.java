package com.example.entities.specifications;


import com.example.entities.Automobile;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AutoSpecs {

    public static Specification  checkParam (String model) {
        return new Specification<Automobile> () {

            List<Predicate> predicateList = new ArrayList<>();
            @Override
            public Predicate toPredicate(Root<Automobile> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if(model != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("automodel"),model));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

    }
}
