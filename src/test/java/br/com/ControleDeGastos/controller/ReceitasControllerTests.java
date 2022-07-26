package br.com.ControleDeGastos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ReceitasControllerTests {
    @Autowired
    private MockMvc mvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testeDeCriacaoDeReceitaComMesmaDescricao() throws Exception{


        String json ="{\"descricao\":\"Dividas de um amigo\",\"valor\":\"800\",\"data\": \"2022-07-05\",\"categoria\":\"OUTRAS\"}";

        mvc.perform(MockMvcRequestBuilders.post("/receitas")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));

    }

    @Test
    public void criacaoDeReceitaQueDeuCerto() throws Exception{
        String json ="{\"descricao\":\"Pagamento de salario\",\"valor\":\"800\",\"data\": \"2022-07-05\",\"categoria\":\"OUTRAS\"}";

        mvc.perform(MockMvcRequestBuilders.post("/receitas")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void atualizacaoQueDeuErrado() throws Exception{
        String json = "{\"descricao\":\"Pagamento de salario\",\"valor\":\"800\",\"data\": \"2022-07-05\",\"categoria\":\"OUTRAS\"}";


        mvc.perform(MockMvcRequestBuilders.put("/receitas/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void atualizacaoQueDeuCerto() throws Exception{
        String json = "{\"descricao\":\"Recebimento de divida\",\"valor\":\"800\",\"data\": \"2022-07-05\"}";


        mvc.perform(MockMvcRequestBuilders.put("/receitas/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deletarReceitaQueDeuCerto() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/receitas/7")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deletarReceitaQueDeuErrado() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/receitas/99")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



}
