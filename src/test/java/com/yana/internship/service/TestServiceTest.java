package com.yana.internship.service;

import com.yana.internship.entity.TestEntity;
import com.yana.internship.repository.TestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;

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
        when(testRepository.save(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity,testService.create(testEntity));

    }
    @Test
    public void createShouldCallRepository() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.save(testEntity)).thenReturn(testEntity);
        testService.create(testEntity);
        verify(testRepository).save(testEntity);
    }

    @Test
    public void updateShouldReturnCorrectResult() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.save(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity,testService.update(testEntity));
    }
    @Test
    public void updateShouldCallRepository() {
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.save(testEntity)).thenReturn(testEntity);
        testService.update(testEntity);
        verify(testRepository).save(testEntity);
    }

    @Test
    public void getShouldReturnCorrectResult() {
        Long i = 1L;
        TestEntity testEntity = mock(TestEntity.class);
        when(testRepository.findById(i)).thenReturn(Optional.ofNullable(testEntity));
        testService.getById(i);
        verify(testRepository).findById(i);
    }
}