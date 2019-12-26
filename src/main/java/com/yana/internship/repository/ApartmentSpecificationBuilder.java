package com.yana.internship.repository;

import com.yana.internship.entity.Address;
import com.yana.internship.entity.Apartment;
import com.yana.internship.entity.Country;
import com.yana.internship.entity.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;

@Component
public class ApartmentSpecificationBuilder {

    public Specification<Apartment> apartmentByCountry(Country code) {
        return (root, query, criteriaBuilder) -> {
            Join<Apartment, Address> addressJoin = root.join("address", JoinType.LEFT);
            Predicate equalPredicate = null;
            if (code != null) {
                equalPredicate = criteriaBuilder.equal(addressJoin.get("country"), code);
            }
            return equalPredicate;
        };
    }

    public Specification<Apartment>

    apartmentByDate(LocalDate checkIn, LocalDate checkOut) {
        return (root, query, criteriaBuilder) -> {
            Join<Apartment, Order> orderJoin = root.join("orders", JoinType.INNER);

            Predicate bothPredicate = criteriaBuilder.and(
                    criteriaBuilder.lessThan(orderJoin.get("checkInDate"), checkIn),
                    criteriaBuilder.greaterThan(orderJoin.get("checkOutDate"), checkOut)
                    );
            Predicate checkInPredicate = criteriaBuilder.and(
                    criteriaBuilder.lessThan(orderJoin.get("checkInDate"), checkIn),
                    criteriaBuilder.greaterThan(orderJoin.get("checkOutDate"), checkIn)
                    );
            Predicate checkOutPredicate = criteriaBuilder.and(
                    criteriaBuilder.lessThan(orderJoin.get("checkInDate"), checkOut),
                    criteriaBuilder.greaterThan(orderJoin.get("checkOutDate"), checkOut)
                    );
            Predicate equalPredicate = criteriaBuilder.or(
                    checkInPredicate,checkOutPredicate,bothPredicate
            );
            return equalPredicate;
        };
    }
}
