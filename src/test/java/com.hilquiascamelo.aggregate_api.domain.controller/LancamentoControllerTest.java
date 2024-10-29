package com.hilquiascamelo.aggregate_api.domain.controller;

import com.hilquiascamelo.aggregate_api.domain.dto.LancamentoDto;
import com.hilquiascamelo.aggregate_api.application.service.LancamentoService;
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
public class LancamentoControllerTest {
    private static final String ENDPOINT_URL = "/api/lancamento";

    @InjectMocks
    private LancamentoController lancamentoController;

    @Mock
    private LancamentoService lancamentoService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(lancamentoController)
                .build();
    }



    @Test
    public void getById() throws Exception {
        Mockito.when(lancamentoService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(LancamentoBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(lancamentoService, Mockito.times(1)).findById(Long.valueOf("1"));
        Mockito.verifyNoMoreInteractions(lancamentoService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(lancamentoService.save(ArgumentMatchers.any(LancamentoDto.class)))
                .thenReturn(LancamentoBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(LancamentoBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(lancamentoService, Mockito.times(1)).save(ArgumentMatchers.any(LancamentoDto.class));
        Mockito.verifyNoMoreInteractions(lancamentoService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(lancamentoService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(LancamentoBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(LancamentoBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(lancamentoService, Mockito.times(1))
                .update(ArgumentMatchers.any(LancamentoDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(lancamentoService);
    }

   // @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(lancamentoService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(LancamentoBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(lancamentoService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(lancamentoService);
    }
}
