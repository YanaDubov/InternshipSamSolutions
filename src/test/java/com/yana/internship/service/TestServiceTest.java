package com.yana.internship.service;

import com.yana.internship.bean.TestEntity;
import com.yana.internship.repository.TestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceTest {
    @Spy
    @InjectMocks
    TestService testService;

    @Mock
    TestRepository testRepository;

    @Test
    public void create() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.create(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity,testService.create(testEntity));
    }

    @Test
    public void update() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.update(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity,testService.update(testEntity));
    }

    @Test
    public void delete() {
        int i = 1;
        when(testRepository.deleteById(i)).thenReturn(i);
        assertEquals(i,testService.deleteById(i));
    }

    @Test
    public void getAll() {
        List<TestEntity> list = new ArrayList<>();
        TestEntity testEntity = new TestEntity();
        testEntity.setName("Yana");
        list.add(testEntity);

        when(testRepository.getAll()).thenReturn(list);
        assertEquals(list,testService.getAll());
    }

    @Test
    public void get() {
        int i = 1;
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.getById(i)).thenReturn(testEntity);
        assertEquals(testEntity,testService.getById(i));
    }
}