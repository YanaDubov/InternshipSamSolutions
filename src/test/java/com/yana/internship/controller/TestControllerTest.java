package com.yana.internship.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.TestEntity;
import com.yana.internship.service.TestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration

public class TestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    TestService testService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testServletContextAndController() {

        ServletContext servletContext = webApplicationContext.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean(TestController.class));
    }

    @Test
    public void getIdTest() throws Exception {

        TestEntity testEntity = new TestEntity();
        testEntity.setId(1);
        testEntity.setName("Yana");
        when(testService.getById(1)).thenReturn(testEntity);
        this.mockMvc
                .perform(get("/test/{id}", 1))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Yana"));
    }

    @Test
    public void getAllTest() throws Exception {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(1);
        testEntity.setName("Yana");
        List<TestEntity> list = new ArrayList<>();
        list.add(testEntity);
        when(testService.getAll()).thenReturn(list);
        this.mockMvc
                .perform(get("/test"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("Yana"));
    }

    @Test
    public void createTest() throws Exception {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(1);
        testEntity.setName("Yana");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(testEntity);
        when(testService.create(testEntity)).thenReturn(testEntity);
        this.mockMvc.perform(post("/test")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(1);
        testEntity.setName("NaNa");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(testEntity);
        when(testService.update(testEntity)).thenReturn(testEntity);
        this.mockMvc.perform(put("/test")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void daleteTest() throws Exception {
        when(testService.deleteById(1)).thenReturn(1);
        this.mockMvc.perform(delete("/test/{id}", 1))
                .andDo(print()).andExpect(status().isOk());
    }

}