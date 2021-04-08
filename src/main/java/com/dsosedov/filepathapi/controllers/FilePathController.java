package com.dsosedov.filepathapi.controllers;

import com.dsosedov.filepathapi.services.FilePathService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilePathController {

    private final FilePathService filePathService;

    public FilePathController(FilePathService filePathService) {
        this.filePathService = filePathService;
    }

    @GetMapping("/api/v1/filepath/valid")
    public String valid(@RequestParam String path) {
        return filePathService.validate(path);
    }

}
