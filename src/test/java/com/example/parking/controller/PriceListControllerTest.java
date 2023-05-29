package com.example.parking.controller;

import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.dto.PriceListDTO;
import com.example.parking.dto.PriceScaleDTO;
import com.example.parking.service.PriceListService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PriceListController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PriceListControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PriceListService priceListService;
    @Autowired
    private ObjectMapper objectMapper;

    private PriceListDTO priceListDTO;
    private PriceScaleDTO priceScaleDTO;
    private ParkingZoneDTO parkingZoneDTO;

    @BeforeEach
    public void init(){
        priceListDTO = PriceListDTO.builder().dateStart(null).dateEnd(null).type("type").priceScaleDTOList(new ArrayList<>()).build();
        priceScaleDTO = PriceScaleDTO.builder().scaleCost(0).scalePerTimeUnit(0).scaleDuration(0).build();
        parkingZoneDTO = ParkingZoneDTO.builder().name("nope").type("nope").parkingSpotDTOList(new ArrayList<>()).build();
    }

    @Test
    void shouldGetPriceListsOfZone() throws Exception{
        List<PriceListDTO> responseList = new ArrayList<>();
        priceListDTO.getPriceScaleDTOList().add(priceScaleDTO);
        responseList.add(priceListDTO);
        when(priceListService.getPriceList(1)).thenReturn(responseList);

        ResultActions response = mockMvc.perform(get("/api/parking-zone/1/priceList").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldAddNewPriceListInAZone() throws Exception{
        priceListDTO.getPriceScaleDTOList().add(priceScaleDTO);
        given(priceListService.addPriceList(priceListDTO, 1)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/parking-zone/1/priceList").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(priceListDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldAddScaleToAPriceList() throws Exception{
        given(priceListService.addPriceScales(priceListDTO.getPriceListId(), priceScaleDTO)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/pricelist/1/priceScale").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(priceScaleDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}