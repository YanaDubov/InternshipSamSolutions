package com.yana.internship.controller;

import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.TestEntity;
import com.yana.internship.service.TestService;
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
    public void getByIdRequest() throws Exception {
        TestEntity testEntity = createEntity("Yana");
        when(testService.getById(1L)).thenReturn(testEntity);
        this.mockMvc
                .perform(get("/test/{id}", 1))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Yana"))
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void getAllRequest() throws Exception {
        TestEntity testEntity = createEntity("Yana");
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
    public void createPostRequestAnd() throws Exception {
        ArgumentCaptor<TestEntity> captor = ArgumentCaptor.forClass(TestEntity.class);
        this.mockMvc.perform(post("/test")
                .contentType(MediaType.APPLICATION_JSON).content("{\"id\":\"1\",\"name\":\"Yana\"}"))
                .andDo(print()).andExpect(status().isCreated());
        verify(testService).create(captor.capture());
        TestEntity actualEntity = captor.getValue();
        assertEquals(actualEntity.getId(), (Long)1L);
        assertEquals(actualEntity.getName(),"Yana");
    }

    @Test
    public void updatePutRequest() throws Exception {
        ArgumentCaptor<TestEntity> captor = ArgumentCaptor.forClass(TestEntity.class);
        this.mockMvc.perform(put("/test")
                .contentType(MediaType.APPLICATION_JSON).content("{\"id\":\"1\",\"name\":\"Yana\"}"))
                .andDo(print()).andExpect(status().isOk());
        verify(testService).update(captor.capture());
        TestEntity actualEntity = captor.getValue();
        assertEquals(actualEntity.getId(),(Long)1L);
        assertEquals(actualEntity.getName(),"Yana");
    }

    @Test
    public void deleteRequest() throws Exception {
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        this.mockMvc.perform(delete("/test/{id}", 1L))
                .andDo(print()).andExpect(status().isOk());
        verify(testService).deleteById(captor.capture());
        Long actualId = captor.getValue();
        assertEquals(actualId,(Long)1L);
    }

    private TestEntity createEntity(String name) {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(1L);
        testEntity.setName(name);
        return testEntity;
    }
}