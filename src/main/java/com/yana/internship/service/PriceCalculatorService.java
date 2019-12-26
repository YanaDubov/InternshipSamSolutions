package com.yana.internship.service;

import com.yana.internship.entity.Tariff;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PriceCalculatorService {

    public BigDecimal countTotalPrice(LocalDate checkIn, LocalDate checkOut, Tariff tariff){
        long daysBetween = DAYS.between(checkIn, checkOut);
        int days = Math.toIntExact(TimeUnit.DAYS.convert(daysBetween, TimeUnit.MILLISECONDS));
        return BigDecimal.valueOf( days ).multiply( tariff.getCostPerNight());
    }
}
