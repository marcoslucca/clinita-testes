package br.com.fundatec.clinicaveterinaria.unit.service;

import br.com.fundatec.clinicaveterinaria.domain.Cachorro;
import br.com.fundatec.clinicaveterinaria.gateway.ClinicaGateway;
import br.com.fundatec.clinicaveterinaria.gateway.resources.Consulta;
import br.com.fundatec.clinicaveterinaria.gateway.resources.ConsultaRequest;
import br.com.fundatec.clinicaveterinaria.repository.CachorroRepository;
import br.com.fundatec.clinicaveterinaria.repository.TutorRepository;
import br.com.fundatec.clinicaveterinaria.service.CachorroService;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class CachorroServiceTest {

    @Autowired
    private CachorroService cachorroService;

    @MockBean
    private TutorRepository tutorRepository;

    @MockBean
    private CachorroRepository cachorroRepository;

    @MockBean
    private ClinicaGateway clinicaGateway;

    @Test
    @DisplayName("Find successfully a dog by id")
    void findById_successful() {
        Cachorro cachorroExpected = new Cachorro();
        long id = 123L;
        cachorroExpected.setId(id);

        Mockito.when(cachorroRepository.findById(id)).thenReturn(Optional.of(cachorroExpected));

        Optional<Cachorro> result = cachorroService.findById(id);

        assertEquals(cachorroExpected, result.get());
    }

    @Test
    @DisplayName("Find successfully a dog by nome")
    void findByNome_successful() {
        Cachorro cachorroExpected = new Cachorro();
        String nome = "Biscoito";
        cachorroExpected.setNome(nome);

        Mockito.when(cachorroRepository.findByNome(nome)).thenReturn(Optional.of(cachorroExpected));

        Optional<Cachorro> result = cachorroService.findByNome(nome);

        assertEquals(cachorroExpected, result.get());
    }

    @Test
    @DisplayName("Find successfully a dog by nome")
    void adicionaConsulta_successful() {
        ConsultaRequest consultaRequest = new ConsultaRequest();
        consultaRequest.setDate(LocalDate.now().toString());
        consultaRequest.setVet("Marcos");

        Consulta resultadoExpected = new Consulta();

        Mockito.when(clinicaGateway.createConsulta(Mockito.any())).thenReturn(resultadoExpected);

        Consulta resultado = cachorroService.adicionaConsulta();

        assertEquals(resultadoExpected, resultado);
    }

}