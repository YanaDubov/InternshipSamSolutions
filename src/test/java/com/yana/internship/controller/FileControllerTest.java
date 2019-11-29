package com.yana.internship.controller;

import com.yana.internship.config.WebConfig;
import com.yana.internship.service.FileService;
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

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class FileControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    FileService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createPostRequestShouldReturnNotNullResult() throws Exception {
        ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);
        this.mockMvc.perform(post("/file")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andDo(print()).andExpect(status().isCreated());
        verify(service).uploadFile(captor.capture());
        byte[] captorData = captor.getValue();
        assertNotNull(captorData);
    }

    @Test
    public void getByIdRequestShouldReturnCorrectHeader() throws Exception {
        byte[] file = new byte[0];
        when(service.downloadByUrl("Test")).thenReturn(file);
        this.mockMvc
                .perform(get("/file/{url}", "Test"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("image/jpeg"));
    }

}