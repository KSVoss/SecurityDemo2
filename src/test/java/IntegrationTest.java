package com.example.securitydemo2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc

public class IntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(roles="Admin")
    void bla() throws Exception{
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    void unauthorized() throws Exception{
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().is4xxClientError());
    }

    @Test
         @WithMockUser(username="bla")
                void username() throws Exception{
        mockMvc.perform(get("/api/users/me"))
                .andExpect(content().string("bla"));
    }
    @Test
    void expectUnauthorized() throws Exception {
        mockMvc.perform(
                 get("/api/hello")
        ).andExpect(
                status().isUnauthorized()
        );
    }
    }

