package com.yana.internship.controller;

import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.Apartment;
import com.yana.internship.service.ApartmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class ApartmentControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    ApartmentService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getByIdRequestShouldReturnCorrectResult() throws Exception {
        Apartment apartment = createApartment(1L);
        when(service.getById(1L)).thenReturn(apartment);
        this.mockMvc
                .perform(get("/apartment/{id}", 1))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value("Test"));
    }

    @Test
    public void getAllRequestShouldReturnCorrectResult() throws Exception {
        Apartment apartment = createApartment(1L);
        List<Apartment> list = new ArrayList<>();
        list.add(apartment);
        when(service.getAll(null, null, null)).thenReturn(list);
        this.mockMvc
                .perform(get("/apartment"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("Test"))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].description").value("Test"));
    }

    @Test
    public void createPostRequestShouldTransferCorrectData() throws Exception {
        ArgumentCaptor<Apartment> captor = ArgumentCaptor.forClass(Apartment.class);
        this.mockMvc.perform(post("/apartment")
                .contentType(MediaType.APPLICATION_JSON).content("{\"id\":\"1\",\"name\":\"Test\",\"description\":\"Test\"}"))
                .andDo(print()).andExpect(status().isCreated());
        verify(service).create(captor.capture());
        Apartment apartment = captor.getValue();
        assertEquals(apartment.getId(), (Long) 1L);
        assertEquals(apartment.getName(), "Test");
        assertEquals(apartment.getDescription(), "Test");
    }

    @Test
    public void deleteRequestShouldTransferCorrectData() throws Exception {
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        this.mockMvc.perform(delete("/apartment/{id}", 1L))
                .andDo(print()).andExpect(status().isOk());
        verify(service).deleteById(captor.capture());
        Long actualId = captor.getValue();
        assertEquals(actualId, (Long) 1L);
    }

    private Apartment createApartment(Long id) {
        Apartment apartment = new Apartment();
        apartment.setId(id);
        apartment.setName("Test");
        apartment.setDescription("Test");
        return apartment;
    }
}