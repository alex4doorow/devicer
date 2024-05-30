package com.afa.devicer.web.controllers;

import com.afa.devicer.core.rest.dto.DtoOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrdersWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@Test
    public void testAddOrder() throws Exception {
        DtoOrder dtoOrder = DtoOrder.builder()
                .orderNum(13L)
                .orderDate(LocalDate.of(2024, 5, 15))
                .build();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String jsonOrder = objectMapper.writeValueAsString(dtoOrder);

        mockMvc.perform(post("/web/v1/orders/add")
                        .param("orderNum", "13")
                        .param("orderDate", "2024-05-15"))
                .andExpect(status().isOk());
    }
}
