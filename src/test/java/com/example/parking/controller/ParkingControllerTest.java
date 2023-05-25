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
    private List<ParkingSpotDTO> spotDTOList;
    private List<ParkingZoneDTO> zoneDTOList;

    @BeforeEach
    public void init(){
        parkingDTO = new ParkingDTO();
        parkingDTO.setName("test_parking");

        zoneDTO = new ParkingZoneDTO();
        zoneDTO.setType("test_type_zone");
        zoneDTO.setName("test_name_zone");

        spotDTO1 = new ParkingSpotDTO();
        spotDTO1.setName("test_name_spot1");
        spotDTO1.setType("test_type_spot1");
        spotDTO1.setOccupied(false);
        spotDTO2 = new ParkingSpotDTO();
        spotDTO2.setName("test_name1_spot2");
        spotDTO2.setType("test_type1_spot2");
        spotDTO2.setOccupied(true);

        spotDTOList = new ArrayList<>();
        zoneDTOList = new ArrayList<>();

        spotDTOList.add(spotDTO1);
        spotDTOList.add(spotDTO2);

        zoneDTO.setParkingSpotDTOList(spotDTOList);
        zoneDTOList.add(zoneDTO);

        parkingDTO.setParkingZoneDTOList(zoneDTOList);
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

        ResultActions response = mockMvc.perform(post("/api/parking/1/parking-zones/1/parking-spots")
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

        when(parkingService.getParkingZones(1)).thenReturn(zoneDTOList);

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
        when(parkingService.findSpotsByZoneId(1)).thenReturn(spotDTOList);

        ResultActions response = mockMvc.perform(get("/api/parking-zones/1/parking-spots").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}