package br.com.fundatec.clinicaveterinaria.unit.service;

import br.com.fundatec.clinicaveterinaria.domain.Tutor;
import br.com.fundatec.clinicaveterinaria.exception.NotFoundException;
import br.com.fundatec.clinicaveterinaria.repository.TutorRepository;
import br.com.fundatec.clinicaveterinaria.service.TutorService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
class TutorServiceTest {

    @Autowired
    private TutorService tutorService;

    @MockBean
    private TutorRepository tutorRepository;

    @Test
    @DisplayName("Find tutors successfully")
    void findAll_successful() {
        List<Tutor> tutores = Arrays.asList(
                new Tutor(
                        1L,
                        "Marcos",
                        30,
                        new ArrayList<>()
                ),
                new Tutor(
                        2L,
                        "Marcos2",
                        30,
                        new ArrayList<>()
                )
        );

        Mockito.when(tutorRepository.findAll()).thenReturn(tutores);

        List<Tutor> resultado = tutorService.findAll();

        assertEquals(tutores, resultado);
    }

    @Test
    @DisplayName("Find tutors by nome successfully")
    void findByNome_successful() {
        Tutor tutor = new Tutor(
                1L,
                "Marcos",
                30,
                new ArrayList<>()
        );
        Optional<Tutor> optionalTutor = Optional.of(tutor);

        Mockito.when(tutorRepository.findByNome("Marcos")).thenReturn(optionalTutor);

        Tutor resultado = tutorService.findByNome("Marcos");

        assertEquals(tutor, resultado);
    }

    @Test
    @DisplayName("Find tutors by nome does not find a tutor")
    void findByNome_exception() {
        Tutor tutor = new Tutor(
                1L,
                "Marcos",
                30,
                new ArrayList<>()
        );
        Mockito.when(tutorRepository.findByNome("Marcos")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> tutorService.findByNome("Marcos"));
    }

}