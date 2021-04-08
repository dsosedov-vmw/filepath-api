package com.dsosedov.filepathapi.services;

import com.dsosedov.filepathapi.repositories.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FilePathServiceTests {

    @Autowired
    private FilePathService filePathService;

    private FileRepository fileRepository;

    @BeforeEach
    void setUp() {
        fileRepository = mock(FileRepository.class);
        filePathService.setFileRepository(fileRepository);
    }

    @Test
    void validate_null() {
        assertEquals("The file path is missing.", filePathService.validate(null));
    }

    @Test
    void validate_empty() {
        assertEquals("The file path is missing.", filePathService.validate(""));
    }

    @Test
    void validate_notNfs() {
        assertEquals("The file path does not belong to the NFS mount.", filePathService.validate("/nt2/dirA"));
    }

    @Test
    void validate_invalid() {
        when(fileRepository.exists(any(String.class))).thenReturn(false);
        assertEquals("The file path is invalid.", filePathService.validate("src/test/resources/dirA"));
    }

    @Test
    void validate_valid() {
        when(fileRepository.exists(any(String.class))).thenReturn(true);
        assertEquals("", filePathService.validate("src/test/resources/dirB"));
    }

}
