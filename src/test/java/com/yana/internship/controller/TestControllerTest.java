package com.yana.internship.controller;

import com.yana.internship.bean.TestEntity;
import com.yana.internship.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestControllerTest {
    @Spy
    @InjectMocks
    TestController testController;

    @Mock
    TestService testService;

    @Test
    public void create() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testService.create(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity,testController.create(testEntity));
    }

    @Test
    public void update() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testService.update(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity,testController.update(testEntity));
    }

    @Test
    public void delete() {
        int i = 1;
        when(testService.deleteById(i)).thenReturn(i);
        assertEquals(i,testController.deleteById(i));
    }

    @Test
    public void getAll() {
        List<TestEntity> list = mock(List.class);
        when(testService.getAll()).thenReturn(list);
        assertEquals(list,testController.getAll());
    }

    @Test
    public void get() {
        int i = 1;
        TestEntity testEntity = mock(TestEntity.class);
        when(testService.getById(i)).thenReturn(testEntity);
        assertEquals(testEntity,testController.get(i));
    }
}