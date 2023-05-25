package com.example.parking.controller;

import com.example.parking.dto.ParkingOccupationDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.service.ParkingOccupationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ParkingOccupationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ParkingOccupationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ParkingOccupationService occupationService;
    @Autowired
    private ObjectMapper objectMapper;

    private ParkingSpotDTO spotDTO;
    private ParkingOccupationDTO occupationDTO;

    @BeforeEach
    void init(){
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        spotDTO = new ParkingSpotDTO("name", "type", false);
        occupationDTO = new ParkingOccupationDTO(1, offsetDateTime, null, 0, "abc1234");
    }

    @Test
    void shouldFindHistoryOfOccupationsInAParking() throws Exception {
        List<ParkingOccupationDTO> responseOcc = new ArrayList<>();
        responseOcc.add(occupationDTO);
        when(occupationService.getParkingHistoryByParkingId(1)).thenReturn(responseOcc);
        ResultActions response = mockMvc.perform(get("/api/parking/1/history")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldSaveNewOccupation() throws Exception {
        given(occupationService.saveParkingOccupation(1, occupationDTO)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/parking-spots/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(occupationDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}