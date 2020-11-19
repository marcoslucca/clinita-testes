package br.com.fundatec.clinicaveterinaria.unit.controller;

import br.com.fundatec.clinicaveterinaria.controller.CachorroController;
import br.com.fundatec.clinicaveterinaria.domain.Cachorro;
import br.com.fundatec.clinicaveterinaria.repository.TutorRepository;
import br.com.fundatec.clinicaveterinaria.service.CachorroService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CachorroControllerTest {

    @MockBean
    private CachorroService cachorroService;

    @MockBean
    private TutorRepository tutorRepository;

    @Autowired
    private CachorroController cachorroController;

    @Test
    @DisplayName("Find successfully all dogs")
    void findAllByNome_without_name() {
        List<Cachorro> cachorros = Arrays.asList(
                new Cachorro(1L, "Biscoito", 2),
                new Cachorro(2L, "Biscoito2", 2)
        );

        Mockito.when(cachorroService.findAll()).thenReturn(cachorros);

        ResponseEntity<List<Cachorro>> resultado = cachorroController.findAllByNome(null);

        assertEquals(cachorros, resultado.getBody());
    }

    @Test
    @DisplayName("Find successfully a dog by nome")
    void findAllByNome_with_name() {
        Cachorro cachorro = new Cachorro(1L, "Biscoito", 2);
        List<Cachorro> cachorros = Arrays.asList(
                cachorro
        );

        Mockito.when(cachorroService.findByNome("Biscoito")).thenReturn(Optional.of(cachorro));

        ResponseEntity<List<Cachorro>> resultado = cachorroController.findAllByNome("Biscoito");

        assertEquals(cachorros, resultado.getBody());
    }

    @Test
    @DisplayName("Does not find a dog by nome")
    void findAllByNome_doesnt_find() {
        List<Cachorro> cachorros = Collections.emptyList();

        Mockito.when(cachorroService.findByNome("Biscoito")).thenReturn(Optional.empty());

        ResponseEntity<List<Cachorro>> resultado = cachorroController.findAllByNome("Biscoito");

        assertEquals(cachorros, resultado.getBody());
    }

}