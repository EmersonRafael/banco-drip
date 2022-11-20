package com.drip.banco.controller;

import com.drip.banco.service.TransferenciaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransferenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TransferenciaService service;

    @Test
    void buscar_tranferencia_sucess() throws Exception{
        MockMvcBuilders
                .standaloneSetup(new TransferenciaController(service))
                .build().perform(MockMvcRequestBuilders.get("/transferencia/origem/1"))
                .andExpect(status().isOk());
    }

    @Test
    void realizar_tranferencia_mesmo_banco_sucess() throws Exception{
        MockMvcBuilders
                .standaloneSetup(new TransferenciaController(service))
                .build().perform(MockMvcRequestBuilders.post("/transferencia/origem/1/destino/2")
                        .content("""
                                {
                                  "valor": 10,
                                  "tipoTransferencia": "Interno"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("Transferencia Realizada"));
    }

    @Test
    void realizar_tranferencia_banco_diferente_sucess() throws Exception{
        MockMvcBuilders
                .standaloneSetup(new TransferenciaController(service))
                .build().perform(MockMvcRequestBuilders.post("/transferencia/origem/1/destino/3")
                        .content("""
                                {
                                  "valor": 10,
                                  "tipoTransferencia": "Externo"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("Transferencia Realizada"));
    }

    @Test
    void realizar_tranferencia_mesma_conta_error() throws Exception{
        MockMvcBuilders
                .standaloneSetup(new TransferenciaController(service))
                .build().perform(MockMvcRequestBuilders.post("/transferencia/origem/1/destino/1")
                        .content("""
                                {
                                  "valor": 10,
                                  "tipoTransferencia": "Interno"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(content().string("Conta Origem e Destino Iguais"));
    }

    @Test
    void realizar_tranferencia_conta_origem_error() throws Exception{
        MockMvcBuilders
                .standaloneSetup(new TransferenciaController(service))
                .build().perform(MockMvcRequestBuilders.post("/transferencia/origem/1000/destino/1")
                        .content("""
                                {
                                  "valor": 10,
                                  "tipoTransferencia": "Interno"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(content().string("Conta Origem nao encontrada = 1000"));
    }

    @Test
    void realizar_tranferencia_conta_destino_error() throws Exception{
        MockMvcBuilders
                .standaloneSetup(new TransferenciaController(service))
                .build().perform(MockMvcRequestBuilders.post("/transferencia/origem/1/destino/1000")
                        .content("""
                                {
                                  "valor": 10,
                                  "tipoTransferencia": "Interno"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(content().string("Conta Destino nao encontrada = 1000"));
    }

    @Test
    void realizar_tranferencia_conta_saldo_error() throws Exception{
        MockMvcBuilders
                .standaloneSetup(new TransferenciaController(service))
                .build().perform(MockMvcRequestBuilders.post("/transferencia/origem/1/destino/2")
                        .content("""
                                {
                                  "valor": 10000,
                                  "tipoTransferencia": "Interno"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(content().string("Conta Origem nao possue saldo para a transferencia"));
    }


}
