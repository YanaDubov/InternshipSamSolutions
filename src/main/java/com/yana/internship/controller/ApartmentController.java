package com.yana.internship.controller;

import com.yana.internship.entity.Apartment;
import com.yana.internship.entity.Country;
import com.yana.internship.service.ApartmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("apartment")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public @ResponseBody
    List<Apartment> getAllByCountryAndDates(@RequestParam(required = false) Country code,
                                            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate checkInDate,
                                            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate checkOutDate) {
        return apartmentService.getAll(code, checkInDate, checkOutDate);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Apartment create(@RequestBody @Valid Apartment apartment) {
        return apartmentService.create(apartment);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        apartmentService.deleteById(id);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Apartment getById(@PathVariable Long id) {
        return this.apartmentService.getById(id);
    }


}
