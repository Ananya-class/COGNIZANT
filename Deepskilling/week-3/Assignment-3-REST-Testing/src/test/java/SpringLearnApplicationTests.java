package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.cognizant.springlearn.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc // [cite: 302]
public class SpringLearnApplicationTests {

    @Autowired
    private CountryController countryController; // [cite: 282]

    @Autowired
    private MockMvc mvc; // [cite: 305]

    @Test
    public void contextLoads() {
        assertNotNull(countryController); // [cite: 286]
    }

    @Test
    public void testGetCountry() throws Exception {
        ResultActions actions = mvc.perform(get("/country")); // [cite: 314]
        actions.andExpect(status().isOk()) // [cite: 321]
                .andExpect(jsonPath("$.code").exists()) // [cite: 328]
                .andExpect(jsonPath("$.code").value("IN")) // [cite: 336]
                .andExpect(jsonPath("$.name").value("India")); // [cite: 338]
    }

    @Test
    public void testGetCountryException() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/az"));
        actions.andExpect(status().isNotFound()); // [cite: 342]
    }
}
