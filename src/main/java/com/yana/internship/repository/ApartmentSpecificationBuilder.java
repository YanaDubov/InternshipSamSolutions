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
import java.util.Date;

@Component
public class ApartmentSpecificationBuilder {

    public Specification<Apartment> apartmentByCountryAndDate(Country code, Date checkIn, Date checkOut) {
        return apartmentByCountry(code).and(apartmentByDate(checkIn, checkOut));
    }

    private Specification<Apartment> apartmentByCountry(Country code) {
        return (root, query, criteriaBuilder) -> {
            Join<Apartment, Address> addressJoin = root.join("address", JoinType.LEFT);
            Predicate equalPredicate = null;
            if (code != null) {
                equalPredicate = criteriaBuilder.equal(addressJoin.get("country"), code);
            }
            return equalPredicate;
        };
    }

    private Specification<Apartment> apartmentByDate(Date checkIn, Date checkOut) {
        return (root, query, criteriaBuilder) -> {
            Join<Apartment, Order> orderJoin = root.join("orders", JoinType.LEFT);
            Predicate equalPredicate = null;
            if (checkIn != null && checkOut != null) {
                equalPredicate = criteriaBuilder.and(
                        criteriaBuilder.between(orderJoin.get("checkInDate"), checkIn, checkOut),
                        criteriaBuilder.between(orderJoin.get("checkOutDate"), checkIn, checkOut)
                ).not();
            }
            return equalPredicate;
        };
    }
}
