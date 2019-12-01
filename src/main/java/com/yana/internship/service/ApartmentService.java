package com.yana.internship.service;

import com.yana.internship.entity.Apartment;
import com.yana.internship.entity.Country;
import com.yana.internship.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ApartmentService {
    private static final Logger logger = LoggerFactory.getLogger(ApartmentService.class);
    private final ApartmentRepository apartmentRepository;
    private final TariffRepository tariffRepository;
    private final AddressRepository addressRepository;
    private final ImageRepository imageRepository;
    private final ApartmentSpecificationBuilder apartmentSpecificationBuilder;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository, TariffRepository tariffRepository, AddressRepository addressRepository, ImageRepository imageRepository, ApartmentSpecificationBuilder apartmentSpecificationBuilder) {
        this.apartmentRepository = apartmentRepository;
        this.tariffRepository = tariffRepository;
        this.addressRepository = addressRepository;
        this.imageRepository = imageRepository;
        this.apartmentSpecificationBuilder = apartmentSpecificationBuilder;
    }

    public Apartment create(Apartment apartment) {
        logger.info("Create apartment");
        tariffRepository.save(apartment.getTariff());
        addressRepository.save(apartment.getAddress());
        imageRepository.saveAll(apartment.getImages());
        return apartmentRepository.save(apartment);
    }

    public Apartment getById(Long id) {
        logger.info("Get apartment with id {}", id);
        return apartmentRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        Apartment apartment = apartmentRepository.findById(id).get();
        tariffRepository.deleteById(apartment.getTariff().getId());
        addressRepository.deleteById(apartment.getAddress().getId());
        apartmentRepository.deleteById(id);
        logger.info("Delete apartment with id {}", apartment.getId());
    }

    public List<Apartment> getAll(Country code, Date checkInDate, Date checkOutDate) {
        List<Apartment> list = apartmentRepository.findAll(apartmentSpecificationBuilder.apartmentByCountryAndDate(code, checkInDate, checkOutDate));
        logger.info("Get {} apartments", list.size());
        return list;
    }
}
