package com.dsosedov.filepathapi.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileRepositoryTests {

    private FileRepository fileRepository;

    @BeforeEach
    void setUp() {
        fileRepository = new FileRepository();
    }

    @Test
    void fileExists_returnsTrue() {
        assertTrue(fileRepository.exists("src/main/resources/application.properties"));
    }

    @Test
    void fileExists_returnsFalse() {
        assertFalse(fileRepository.exists("src/main/resources/missing.txt"));
    }

}
