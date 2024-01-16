package com.befrank.casedeveloperjava.controllers;

import com.befrank.casedeveloperjava.models.Pension;
import com.befrank.casedeveloperjava.services.PensionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PensionControllerTest {

    @Mock
    private PensionService pensionService;
    @InjectMocks
    private PensionController pensionController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pensionController).build();
    }

    @Test
    void testCalculate() throws Exception {
        Pension expectedPension = Pension.builder()
                .expectedPension(BigDecimal.valueOf(10000))
                .forAge(65)
                .build();
        when(pensionService.getExpectedPensionValue(anyLong(), anyInt())).thenReturn(expectedPension);

        MvcResult result = mockMvc.perform(get("/pension/{id}", 1L)
                        .param("verwachtePensioenLeeftijd", "65"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        Pension pension = objectMapper.readValue(contentAsString, Pension.class);
        Assertions.assertEquals(65, pension.getForAge());
        Assertions.assertEquals(BigDecimal.valueOf(10000), pension.getExpectedPension());

    }
}
