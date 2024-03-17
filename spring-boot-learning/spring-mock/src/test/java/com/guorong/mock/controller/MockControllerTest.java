package com.guorong.mock.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class MockControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new MockController())
                .build();
    }

    @Test
    public void testHelloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/mock/hello_world"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello world"));
    }

}
