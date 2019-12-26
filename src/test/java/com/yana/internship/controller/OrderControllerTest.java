package com.yana.internship.controller;

import com.yana.internship.config.WebConfig;
import com.yana.internship.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class OrderControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    OrderService service;
    private final SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void deleteRequestShouldReturnCorrectResult() throws Exception {
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        this.mockMvc.perform(delete("/order/delete/{id}", 1L))
                .andDo(print()).andExpect(status().isOk());
        verify(service).delete(captor.capture());
        Long actualId = captor.getValue();
        assertEquals(actualId, (Long) 1L);
    }
}