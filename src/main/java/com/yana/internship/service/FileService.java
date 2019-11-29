package com.yana.internship.service;

import com.yana.internship.entity.File;
import com.yana.internship.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public byte[] downloadByUrl(String url) {
        logger.info("Download file by url {}", url);
        File file = fileRepository.findByUrl(url);
        return file.getFile();
    }

    public String uploadFile(byte[] content) {
        File file = new File();
        file.setFile(content);
        file.setUrl(UUID.randomUUID().toString());
        logger.info("Upload file");
        return fileRepository.save(file).getUrl();
    }

}
