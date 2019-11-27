package com.yana.internship.service;

import com.yana.internship.entity.File;
import com.yana.internship.repository.FileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceTest {
    @Spy
    @InjectMocks
    FileService fileService;

    @Mock
    FileRepository fileRepository;

    @Test
    public void uploadShouldReturnCorrectResult() {
        File file = mock(File.class);
        when(fileRepository.save(file)).thenReturn(file);
        assertEquals(file, fileService.uploadFile(file));
    }

    @Test
    public void uploadShouldCallRepository() {
        File file = mock(File.class);
        when(fileRepository.save(file)).thenReturn(file);
        fileService.uploadFile(file);
        verify(fileRepository).save(file);
    }

    @Test
    public void downloadByUrlShouldReturnCorrectResult() {
        String url = "/";
        File file = mock(File.class);
        when(fileRepository.findByUrl(url)).thenReturn(file);
        assertEquals(file, fileService.downloadByUrl(url));
    }

    @Test
    public void downloadByUrlShouldCallRepository() {
        String url = "/";
        File file = mock(File.class);
        when(fileRepository.findByUrl(url)).thenReturn(file);
        fileService.downloadByUrl(url);
        verify(fileRepository).findByUrl(url);
    }
}