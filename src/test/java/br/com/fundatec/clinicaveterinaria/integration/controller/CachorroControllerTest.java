package br.com.fundatec.clinicaveterinaria.integration.controller;

import br.com.fundatec.clinicaveterinaria.controller.request.TutorRequest;
import br.com.fundatec.clinicaveterinaria.domain.Cachorro;
import br.com.fundatec.clinicaveterinaria.domain.Tutor;
import br.com.fundatec.clinicaveterinaria.repository.CachorroRepository;
import br.com.fundatec.clinicaveterinaria.repository.TutorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class CachorroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CachorroRepository cachorroRepository;

    @Autowired
    TutorRepository tutorRepository;

    @Test
    public void retornaTodosCachorros_ok() throws Exception {
        Cachorro cachorro = new Cachorro(1L, "Biscoito", 2);
        cachorroRepository.saveAndFlush(cachorro);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/cachorros"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Biscoito"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idade").value(2));
    }

    @Test
    public void retornaCachorro_ok() throws Exception {
        Cachorro cachorro = new Cachorro(1L, "Biscoito", 2);
        cachorroRepository.saveAndFlush(cachorro);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/cachorros/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Biscoito"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idade").value(2));
    }

    @Test
    public void salvaCachorro_ok() throws Exception {
        Cachorro cachorro = new Cachorro(1L, "Biscoito", 2);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cachorro);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/cachorros")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Biscoito"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idade").value(2));
    }

    @Test
    public void atualizaCachorro_ok() throws Exception {
        Tutor tutor = new Tutor();
        tutor.setCachorros(new ArrayList<>());
        tutor.setId(1L);
        tutor.setIdade(40);
        tutor.setNome("Marcos");
        tutorRepository.saveAndFlush(tutor);

        Cachorro cachorro = new Cachorro(1L, "Biscoito", 2);
        cachorroRepository.saveAndFlush(cachorro);

        TutorRequest tutorRequest = new TutorRequest();
        tutorRequest.setNome("Marcos");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(tutorRequest);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.patch("/cachorros/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Biscoito"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idade").value(2));
    }

}
