package com.yana.internship.controller;

import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.File;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    public void createPostRequest() throws Exception {
        ArgumentCaptor<File> captor = ArgumentCaptor.forClass(File.class);
        this.mockMvc.perform(post("/file")
                .contentType(MediaType.APPLICATION_JSON).content("{\"id\":\"1\",\"url\":\"Test\",\"file\":\"Test\"}"))
                .andDo(print()).andExpect(status().isCreated());
        verify(service).uploadFile(captor.capture());
        File file = captor.getValue();
        assertEquals(file.getId(), (Long) 1L);
        assertEquals(file.getUrl(), "Test");
    }

    @Test
    public void getByIdRequest() throws Exception {
        File file = createFile();
        when(service.downloadByUrl("Test")).thenReturn(file);
        this.mockMvc
                .perform(get("/file/{url}", "Test"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.url").value("Test"));
    }

    private File createFile() {
        File file = new File();
        file.setId(1L);
        file.setUrl("Test");
        file.setFile(new byte[0]);
        return file;
    }

}