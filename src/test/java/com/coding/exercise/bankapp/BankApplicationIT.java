package com.coding.exercise.bankapp;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.common.io.Files;

@ExtendWith( SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankApplicationIT {

    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(BankApplicationIT.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addsCustomer100() throws Exception {
        
    	ClassLoader classLoader = getClass().getClassLoader();
    	String json="";
    	try (InputStream is = classLoader.getResourceAsStream("AddCustomer_1000.json")) {
    	     
    	        try (InputStreamReader isr = new InputStreamReader(is);
    	             BufferedReader reader = new BufferedReader(isr)) {
    	            json= reader.lines().collect(Collectors.joining(System.lineSeparator()));
    	        }
    	    }
    	log.debug("json");
      	String newRide = "{\"name\":\"Monorail\",\"description\":\"Sedate travelling ride.\",\"thrillFactor\":2,\"vomitFactor\":1}";
        mockMvc.perform(MockMvcRequestBuilders.post("/customers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void addsCustomer1001() throws Exception {
        
    	ClassLoader classLoader = getClass().getClassLoader();
    	String json="";
    	try (InputStream is = classLoader.getResourceAsStream("AddCustomer_1001.json")) {
    	     
    	        try (InputStreamReader isr = new InputStreamReader(is);
    	             BufferedReader reader = new BufferedReader(isr)) {
    	            json= reader.lines().collect(Collectors.joining(System.lineSeparator()));
    	        }
    	    }
    	log.debug("json");
      	String newRide = "{\"name\":\"Monorail\",\"description\":\"Sedate travelling ride.\",\"thrillFactor\":2,\"vomitFactor\":1}";
        mockMvc.perform(MockMvcRequestBuilders.post("/customers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void getsAllCustomerss() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getsSingleRide() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

   // @Test
    public void returnsNotFoundForInvalidSingleRide() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

   
}