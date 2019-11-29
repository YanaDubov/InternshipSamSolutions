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
        String url = "/";
        byte[] fileBytes = new byte[0];
        File file = createFile(url, fileBytes);
        when(fileRepository.save(any(File.class))).thenReturn(file);
        assertEquals(url, fileService.uploadFile(fileBytes));
    }

    @Test
    public void uploadShouldCallRepository() {
        String url = "/";
        byte[] fileBytes = new byte[0];
        File file = createFile(url, fileBytes);
        when(fileRepository.save(any(File.class))).thenReturn(file);
        fileService.uploadFile(fileBytes);
        verify(fileRepository).save(any(File.class));
    }

    @Test
    public void downloadByUrlShouldReturnCorrectResult() {
        String url = "/";
        byte[] fileBytes = new byte[0];
        File file = createFile(url, fileBytes);
        when(fileRepository.findByUrl(url)).thenReturn(file);
        assertEquals(fileBytes, fileService.downloadByUrl(url));
    }

    @Test
    public void downloadByUrlShouldCallRepository() {
        String url = "/";
        byte[] fileBytes = new byte[0];
        File file = createFile(url, fileBytes);
        when(fileRepository.findByUrl(url)).thenReturn(file);
        fileService.downloadByUrl(url);
        verify(fileRepository).findByUrl(url);
    }

    private File createFile(String url, byte[] fileBytes) {
        File file = new File();
        file.setId(1L);
        file.setUrl(url);
        file.setFile(fileBytes);
        return file;
    }
}