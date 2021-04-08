package com.dsosedov.filepathapi.components;

import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileComponent {

    public Boolean exists(String path) {
        return Files.exists(Path.of(path));
    }

}
