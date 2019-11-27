package com.yana.internship.repository;

import com.yana.internship.entity.Apartment;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long>, JpaSpecificationExecutor<Apartment> {
}
