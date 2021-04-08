package com.dsosedov.filepathapi.repositories;

import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;

@Repository
public class FileRepository {

    public Boolean exists(String path) {
        return Files.exists(Path.of(path));
    }

}
