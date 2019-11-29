package com.yana.internship.service;

import com.yana.internship.entity.Address;
import com.yana.internship.entity.Apartment;
import com.yana.internship.entity.Image;
import com.yana.internship.entity.Tariff;
import com.yana.internship.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApartmentServiceTest {

    @Spy
    @InjectMocks
    ApartmentService apartmentService;

    @Mock
    ApartmentRepository apartmentRepository;
    @Mock
    TariffRepository tariffRepository;
    @Mock
    AddressRepository addressRepository;
    @Mock
    ImageRepository imageRepository;
    @Mock
    ApartmentSpecificationBuilder apartmentSpecificationBuilder;
    @Mock
    Tariff tariff;
    @Mock
    Address address;
    @Mock
    List<Image> imageList;

    @Test
    public void createShouldReturnCorrectResult() {
        Apartment apartment = createApartment();
        when(apartmentRepository.save(apartment)).thenReturn(apartment);
        assertEquals(apartment, apartmentService.create(apartment));
    }

    @Test
    public void createShouldCallTariffRepository() {
        Apartment apartment = createApartment();
        when(apartmentRepository.save(apartment)).thenReturn(apartment);
        apartmentService.create(apartment);
        verify(tariffRepository).save(tariff);
    }

    @Test
    public void createShouldCallAddressRepository() {
        Apartment apartment = createApartment();
        when(apartmentRepository.save(apartment)).thenReturn(apartment);
        apartmentService.create(apartment);
        verify(addressRepository).save(address);
    }

    @Test
    public void createShouldCallApartmentRepository() {
        Apartment apartment = createApartment();
        when(apartmentRepository.save(apartment)).thenReturn(apartment);
        apartmentService.create(apartment);
        verify(apartmentRepository).save(apartment);
    }

    @Test
    public void createShouldCallImageRepository() {
        Apartment apartment = createApartment();
        when(apartmentRepository.save(apartment)).thenReturn(apartment);
        apartmentService.create(apartment);
        verify(imageRepository).saveAll(imageList);
    }

    @Test
    public void getShouldCallRepository() {
        Long i = 1L;
        Apartment apartment = mock(Apartment.class);
        when(apartmentRepository.findById(i)).thenReturn(Optional.ofNullable(apartment));
        apartmentService.getById(i);
        verify(apartmentRepository).findById(i);
    }

    @Test
    public void getShouldReturnCorrectResult() {
        Long i = 1L;
        Apartment apartment = mock(Apartment.class);
        when(apartmentRepository.findById(i)).thenReturn(Optional.ofNullable(apartment));
        assertEquals(apartment, apartmentService.getById(i));
    }

    @Test
    public void getAllShouldCallRepository() {
        List<Apartment> apartments = mock(List.class);
        Specification specification = mock(Specification.class);
        when(apartmentSpecificationBuilder.apartmentByCountryAndDate(null, null, null)).thenReturn(specification);
        when(apartmentRepository.findAll(any((Specification.class)))).thenReturn(apartments);
        apartmentService.getAll(null, null, null);
        verify(apartmentRepository).findAll(any(Specification.class));
    }

    @Test
    public void getAllShouldReturnCorrectResult() {
        List<Apartment> apartments = mock(List.class);
        Specification specification = mock(Specification.class);
        when(apartmentSpecificationBuilder.apartmentByCountryAndDate(null, null, null)).thenReturn(specification);
        when(apartmentRepository.findAll(any((Specification.class)))).thenReturn(apartments);
        assertEquals(apartments, apartmentService.getAll(null, null, null));
    }

    private Apartment createApartment() {
        Apartment apartment = new Apartment();
        when(tariffRepository.save(tariff)).thenReturn(tariff);
        apartment.setTariff(tariff);
        when(addressRepository.save(address)).thenReturn(address);
        apartment.setAddress(address);
        when(imageRepository.saveAll(imageList)).thenReturn(imageList);
        apartment.setImages(imageList);
        return apartment;
    }
}