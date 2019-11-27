package com.yana.internship.controller;

import com.yana.internship.entity.File;
import com.yana.internship.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{url}")
    public File downloadFileByUrl(@PathVariable String url) {
        return fileService.downloadByUrl(url);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public File uploadFile(@RequestBody @Valid File file) {
        return fileService.uploadFile(file);
    }

}
