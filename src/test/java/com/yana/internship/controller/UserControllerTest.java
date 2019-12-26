package com.yana.internship.controller;

import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.User;
import com.yana.internship.service.UserService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    UserService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createPostRequestShouldTransferCorrectData() throws Exception {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Yana\",\"surname\":\"Dubovskaya\",\"email\":\"dubov159@gmail.com\",\"password\":\"123456\",\"roles\":[]}"))
                .andDo(print()).andExpect(status().isCreated());
        verify(service).create(captor.capture());
        User apartment = captor.getValue();
        assertEquals(apartment.getName(), "Yana");
        assertEquals(apartment.getSurname(), "Dubovskaya");
        assertEquals(apartment.getEmail(), "dubov159@gmail.com");
        assertEquals(apartment.getPassword(), "123456");

    }
}