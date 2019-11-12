package com.yana.internship.service;

import com.yana.internship.entity.Lang;
import com.yana.internship.entity.TestEntity;
import com.yana.internship.repository.TestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceTest {
    @Spy
    @InjectMocks
    TestService testService;

    @Mock
    TestRepository testRepository;

    @Test
    public void createShouldReturnCorrectResult() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.create(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity,testService.create(testEntity));

    }
    @Test
    public void createShouldCallRepository() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.create(testEntity)).thenReturn(testEntity);
        testService.create(testEntity);
        verify(testRepository).create(testEntity);
    }

    @Test
    public void updateShouldReturnCorrectResult() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.update(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity,testService.update(testEntity));
    }
    @Test
    public void updateShouldCallRepository() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.update(testEntity)).thenReturn(testEntity);
        testService.update(testEntity);
        verify(testRepository).update(testEntity);
    }

    @Test
    public void deleteShouldReturnCorrectResult() {
        int i = 1;
        when(testRepository.deleteById(i)).thenReturn(i);
        assertEquals(i,testService.deleteById(i));
    }
    @Test
    public void deleteShouldCallRepository() {
        int i = 1;
        when(testRepository.deleteById(i)).thenReturn(i);
        testService.deleteById(i);
        verify(testRepository).deleteById(i);
    }
//
//    @Test
//    public void getAllShouldReturnCorrectResult() {
//        List<TestEntity> list = new ArrayList<>();
//        TestEntity testEntity = mock(TestEntity.class);
//        when(testRepository.getAll()).thenReturn(list);
//        list.add(testEntity);
//        assertEquals(list.contains(testEntity),testService.getAll().contains(testEntity));
//    }
//    @Test
//    public void getAllShouldCallRepository() {
//        List<TestEntity> list = new ArrayList<>();
//        TestEntity testEntity = mock(TestEntity.class);
//        when(testRepository.getAll()).thenReturn(list);
//        list.add(testEntity);
//        testService.getAll();
//        verify(testRepository).getAll();
//    }

    @Test
    public void getShouldReturnCorrectResult() {
        int i = 1;
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.getById(i, Lang.en)).thenReturn(testEntity);
        testService.getById(i,Lang.en);
        verify(testRepository).getById(i,Lang.en);
    }
}