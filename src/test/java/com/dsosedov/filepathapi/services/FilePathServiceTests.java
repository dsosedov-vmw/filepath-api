package com.dsosedov.filepathapi.services;

import com.dsosedov.filepathapi.components.FileComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilePathServiceTests {

    private FilePathService filePathService;

    @Mock
    private FileComponent fileComponent;

    @BeforeEach
    void setUp() {
        filePathService = new FilePathService(fileComponent, "n1");
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
        when(fileComponent.exists(anyString())).thenReturn(false);
        assertEquals("The file path is invalid.", filePathService.validate("/n1/dirA"));
    }

    @Test
    void validate_valid() {
        when(fileComponent.exists(anyString())).thenReturn(true);
        assertEquals("", filePathService.validate("/n1/dirB"));
    }

}
