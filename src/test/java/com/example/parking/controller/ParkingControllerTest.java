package com.example.parking.controller;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.service.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ParkingController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ParkingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;
    @Autowired
    private ObjectMapper objectMapper;

    private ParkingDTO parkingDTO;
    private ParkingZoneDTO zoneDTO;
    private ParkingSpotDTO spotDTO1;
    private ParkingSpotDTO spotDTO2;

    @BeforeEach
    public void init(){
        parkingDTO = ParkingDTO.builder().name("test_park").parkingZoneDTOList(new ArrayList<>()).build();
        zoneDTO = ParkingZoneDTO.builder().name("test_zone").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        spotDTO1 = ParkingSpotDTO.builder().name("name").type("name").occupied(false).build();
        spotDTO2 = ParkingSpotDTO.builder().name("name").type("name").occupied(true).build();

        zoneDTO.getParkingSpotDTOList().add(spotDTO1);
        zoneDTO.getParkingSpotDTOList().add(spotDTO2);

        parkingDTO.getParkingZoneDTOList().add(zoneDTO);

    }

    @Test
    public void parkingController_addParking_ReturnParking() throws Exception{

        given(parkingService.addParking(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response =mockMvc.perform(post("/api/parking")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void parkingController_addZone_ReturnZone() throws Exception{

        given(parkingService.addZone(parkingDTO.getParkingId(), zoneDTO)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response =mockMvc.perform(post("/api/parking/1/parking-zones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(zoneDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void parkingController_addSpot_ReturnSpot() throws Exception{

        given(parkingService.addSpot(spotDTO1, zoneDTO.getParkingZoneId())).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/parking-zones/1/parking-spots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(spotDTO1)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void parkingController_getParkings_ReturnResponseDTO() throws Exception{
        List<ParkingDTO> parkingResponse = new ArrayList<>();
        parkingResponse.add(parkingDTO);

        when(parkingService.findAllParkings()).thenReturn(parkingResponse);

        ResultActions response = mockMvc.perform(get("/api/parking").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void shouldReturnTheSelectedParking() throws Exception {

        when(parkingService.findParkingById(1)).thenReturn(parkingDTO);

        ResultActions response = mockMvc.perform(get("/api/parking/1").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReturnAllZonesInAParking() throws Exception {

        when(parkingService.getParkingZones(1)).thenReturn(parkingDTO.getParkingZoneDTOList());

        ResultActions response = mockMvc.perform(get("/api/parking/1/parking-zones").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReturnSelectedZoneInAParking() throws Exception {

        when(parkingService.getParkingZoneById(1, 1)).thenReturn(zoneDTO);

        ResultActions response = mockMvc.perform(get("/api/parking/1/parking-zones/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void shouldReturnSelectedSpot() throws Exception{

        when(parkingService.findParkingSpotById(1)).thenReturn(spotDTO1);

        ResultActions response = mockMvc.perform(get("/api/parking-spots/1").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReturnSpotsInAZone() throws Exception{
        when(parkingService.findSpotsByZoneId(1)).thenReturn(zoneDTO.getParkingSpotDTOList());

        ResultActions response = mockMvc.perform(get("/api/parking-zones/1/parking-spots").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReturnEmptySpots() throws Exception{
        List<ParkingSpotDTO> response = new ArrayList<>();
        response.add(spotDTO2);
        when(parkingService.findEmptySpots(1)).thenReturn(response);

        ResultActions answer = mockMvc.perform(get("/api/parking/1/empty-spots").contentType(MediaType.APPLICATION_JSON));

        answer.andExpect(MockMvcResultMatchers.status().isOk());
    }

}