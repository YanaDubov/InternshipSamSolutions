package com.yana.internship.repository;

import com.yana.internship.entity.Tariff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends CrudRepository<Tariff, Integer> {
}
