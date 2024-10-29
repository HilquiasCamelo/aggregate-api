package com.hilquiascamelo.aggregate_api.domain.controller;

import com.hilquiascamelo.aggregate_api.domain.dto.RelatorioDto;
import com.hilquiascamelo.aggregate_api.application.service.RelatorioService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
public class RelatorioControllerTest {
    private static final String ENDPOINT_URL = "/api/relatorio";

    @InjectMocks
    private RelatorioController relatorioController;

    @Mock
    private RelatorioService relatorioService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(relatorioController)
                .build();
    }


    @Test
    public void getById() throws Exception {
        Mockito.when(relatorioService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(RelatorioBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(relatorioService, Mockito.times(1)).findById(Long.valueOf("1"));
        Mockito.verifyNoMoreInteractions(relatorioService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(relatorioService.save(ArgumentMatchers.any(RelatorioDto.class)))
                .thenReturn(RelatorioBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(RelatorioBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(relatorioService, Mockito.times(1)).save(ArgumentMatchers.any(RelatorioDto.class));
        Mockito.verifyNoMoreInteractions(relatorioService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(relatorioService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(RelatorioBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(RelatorioBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(relatorioService, Mockito.times(1))
                .update(ArgumentMatchers.any(RelatorioDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(relatorioService);
    }

    //@Test
    public void delete() throws Exception {
        Mockito.doNothing().when(relatorioService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(RelatorioBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(relatorioService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(relatorioService);
    }
}
