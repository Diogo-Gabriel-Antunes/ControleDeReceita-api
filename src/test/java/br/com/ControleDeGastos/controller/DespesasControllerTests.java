package br.com.ControleDeGastos.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class DespesasControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void CadastroQueDeuErrado() throws Exception{
        String json ="{\"descricao\":\"Dividas de um amigo\",\"valor\":\"800\",\"data\": \"2022-07-05\",\"categoria\":\"OUTRAS\"}";

        mvc.perform(MockMvcRequestBuilders.post("/despesas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void CadastroQueDeuCerto() throws Exception{
        String json ="{\"descricao\":\"Dividas para amigo\",\"valor\":\"800\",\"data\": \"2022-07-05\",\"categoria\":\"OUTRAS\"}";

        mvc.perform(MockMvcRequestBuilders.post("/despesas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void atualizacaoQueDeuErrado() throws Exception{
        String json ="{\"descricao\":\"Dividas para amigo\",\"valor\":\"800\",\"data\": \"2022-07-05\",\"categoria\":\"OUTRAS\"}";

        mvc.perform(MockMvcRequestBuilders.put("/despesas/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void atualizacaoQueDeuCerto() throws Exception{
        String json ="{\"descricao\":\"Conta de luz\",\"valor\":\"800\",\"data\": \"2022-07-05\",\"categoria\":\"OUTRAS\"}";

        mvc.perform(MockMvcRequestBuilders.put("/despesas/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deletarQueDeuCerto() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/despesas/4")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deletarQueDeuErrado() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/despesas/00")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getDespesasPorId() throws Exception{


        mvc.perform(MockMvcRequestBuilders.get("/despesas/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao",Matchers.is("Conta de luz")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valor",Matchers.is(800.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.is("2022-07-05")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoria",Matchers.is("OUTRAS")));
    }
}
