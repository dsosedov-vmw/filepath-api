package com.dsosedov.filepathapi.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileComponentTests {

    private FileComponent fileComponent;

    @BeforeEach
    void setUp() {
        fileComponent = new FileComponent();
    }

    @Test
    void fileExists_returnsTrue() {
        assertTrue(fileComponent.exists("src/main/resources/application.properties"));
    }

    @Test
    void fileExists_returnsFalse() {
        assertFalse(fileComponent.exists("src/main/resources/missing.txt"));
    }

}
