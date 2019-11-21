package com.yana.internship.repository;

import com.yana.internship.entity.Apartment;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
}
