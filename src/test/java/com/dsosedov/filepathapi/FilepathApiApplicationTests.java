package com.dsosedov.filepathapi;

import com.dsosedov.filepathapi.services.FilePathService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FilepathApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilePathService filePathService;

    @Test
    void contextLoads() {
    }

    @Test
    void filePath_valid_ok() throws Exception {
        when(filePathService.validate(any(String.class))).thenReturn("foobar");
        mockMvc
                .perform(get("/api/v1/filepath/valid?path=/nt1/dirA"))
                .andExpect(status().isOk())
                .andExpect(content().string("foobar"));
    }

}
