package com.yana.internship.service;

import com.yana.internship.entity.Tariff;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class PriceCalculatorServiceTest {

    private LocalDate checkIn;
    private LocalDate checkOut;
    private Tariff tariff;
    private BigDecimal result;

    @Spy
    @InjectMocks
    PriceCalculatorService priceCalculatorService;

    public PriceCalculatorServiceTest(LocalDate checkIn, LocalDate checkOut, Tariff tariff, BigDecimal result) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.tariff = tariff;
        this.result = result;
    }

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        Tariff tariff = new Tariff();
        tariff.setId(1L);
        tariff.setCostPerNight(BigDecimal.valueOf(1));
        return Arrays.asList(new Object[][]{
                {LocalDate.now(), LocalDate.now().plusDays(9), tariff, BigDecimal.valueOf(9)},
                {LocalDate.now(), LocalDate.now().plusDays(3), tariff, BigDecimal.valueOf(3)},
                {LocalDate.now(), LocalDate.now().plusDays(12), tariff, BigDecimal.valueOf(12)},
        });
    }

    @Test
    public void shouldReturnExpectedResult() {
        BigDecimal resultPrice = priceCalculatorService.countTotalPrice(checkIn, checkOut, tariff);
        assertEquals(resultPrice, result);
    }
}