package com.yana.internship.service;

import com.yana.internship.entity.File;
import com.yana.internship.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File downloadByUrl(String url) {
        logger.info("Download file by url {}", url);
        return fileRepository.findByUrl(url);
    }

    public File uploadFile(File file) {
        logger.info("Upload file with id {}", file.getId());
        return fileRepository.save(file);
    }

}
