package com.example.parking.controller;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
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
    private ParkingZoneDTO parkingZoneDTO;
    private ParkingSpotDTO parkingSpotDTO;
    private ParkingSpot parkingSpot;

    private List<ParkingSpotDTO> parkingSpotDTOList;

    private List<ParkingZoneDTO> parkingZoneDTOList;

    @BeforeEach
    public void init(){
        parkingDTO = new ParkingDTO();
        parkingDTO.setName("test");
        parkingDTO.setParkingZoneDTOList(parkingZoneDTOList);

        parkingZoneDTO = new ParkingZoneDTO();
        parkingZoneDTO.setType("typeZone");
        parkingZoneDTO.setName("nameZone");
        parkingZoneDTO.setParkingSpotDTOList(parkingSpotDTOList);

        parkingSpotDTO = new ParkingSpotDTO();
        parkingSpotDTO.setName("nameSpot");
        parkingSpotDTO.setType("TypeSpot");
        parkingSpotDTO.setOccupied(false);

        List<ParkingSpot> parkingSpots = new ArrayList<>();
        parkingSpots.add(parkingSpot);
        Parking parking = Parking.builder().name("nameParking").build();
        ParkingZone parkingZone = ParkingZone.builder().type("ZoneType").name("nameZone").parkingSpots(parkingSpots).build();
        parkingSpot = ParkingSpot.builder().build();
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

        given(parkingService.addZone(parkingDTO.getParkingId(),parkingZoneDTO)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response =mockMvc.perform(post("/api/parking/1/parking-zones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingZoneDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void parkingController_addSpot_ReturnSpot() throws Exception{

        given(parkingService.addSpot(parkingSpotDTO,parkingZoneDTO.getParkingZoneId())).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/parking/1/parking-zones/1/parking-spots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingSpotDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void parkingController_getParkings_ReturnResponseDTO() throws Exception{
        List<ParkingSpotDTO> spotList = new ArrayList<>();
        ParkingZoneDTO zoneDTO = new ParkingZoneDTO("ax", "nai");
        List<ParkingZoneDTO> zonesDTO = new ArrayList<>();
        zonesDTO.add(zoneDTO);
        ParkingDTO parkingDTO1 = new ParkingDTO("get_park_test", zonesDTO);
        List<ParkingDTO> parkingResponse = new ArrayList<>();
        parkingResponse.add(parkingDTO1);

        when(parkingService.findAllParkings()).thenReturn(parkingResponse);

        ResultActions response = mockMvc.perform(get("/api/parking").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }


}