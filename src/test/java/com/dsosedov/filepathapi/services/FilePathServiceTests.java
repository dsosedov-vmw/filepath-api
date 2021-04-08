package com.dsosedov.filepathapi.services;

import com.dsosedov.filepathapi.components.FileComponent;
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

    private FileComponent fileComponent;

    @BeforeEach
    void setUp() {
        fileComponent = mock(FileComponent.class);
        filePathService.setFileComponent(fileComponent);
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
        assertEquals("The file path does not belong to the NFS mount.", filePathService.validate("/n2/dirA"));
    }

    @Test
    void validate_invalid() {
        when(fileComponent.exists(any(String.class))).thenReturn(false);
        assertEquals("The file path is invalid.", filePathService.validate("/n1/dirA"));
    }

    @Test
    void validate_valid() {
        when(fileComponent.exists(any(String.class))).thenReturn(true);
        assertEquals("", filePathService.validate("/n1/dirB"));
    }

}
