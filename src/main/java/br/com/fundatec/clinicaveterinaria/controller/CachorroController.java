package br.com.fundatec.clinicaveterinaria.controller;

import br.com.fundatec.clinicaveterinaria.controller.request.TutorRequest;
import br.com.fundatec.clinicaveterinaria.domain.Cachorro;
import br.com.fundatec.clinicaveterinaria.domain.Tutor;
import br.com.fundatec.clinicaveterinaria.repository.TutorRepository;
import br.com.fundatec.clinicaveterinaria.service.CachorroService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cachorros")
public class CachorroController {

    @Autowired
    private CachorroService cachorroService;

    @Autowired
    private TutorRepository tutorRepository;

    @GetMapping("{id}")
    public ResponseEntity<Cachorro> retornaCachorro(@PathVariable("id") Long id) {
        Optional<Cachorro> resultado = cachorroService.findById(id);

        if (resultado.isPresent()) {
            return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cachorro>> findAllByNome(@RequestParam(value = "nome", required = false) String nome) {
        List<Cachorro> resultado;

        if (nome != null) {
            Optional<Cachorro> cachorro = cachorroService.findByNome(nome);

            if (cachorro.isPresent()) {

//                if(nome == "Biscoito") {
//                    resultado = Collections.emptyList();
//                } else {
                resultado = Collections.singletonList(cachorro.get());
//                }
            } else {
                resultado = Collections.emptyList();
            }
        } else {
            resultado = cachorroService.findAll();
        }

        return new ResponseEntity(resultado, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity salvaCachorro(@RequestBody Cachorro cachorro) {
        Cachorro saved = cachorroService.save(cachorro);

        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<List<Tutor>> adicionaTutor(@Valid @RequestBody TutorRequest tutorRequest, @PathVariable String id) {
        Optional<Cachorro> cachorroOptional = cachorroService.findById(Long.valueOf(id));

        if (cachorroOptional.isPresent()) {
            Cachorro cachorro = cachorroOptional.get();
            Optional<Tutor> tutorOptional = tutorRepository.findByNome(tutorRequest.getNome());

            if (tutorOptional.isPresent()) {
                Tutor tutor = tutorOptional.get();
                cachorro.setTutor(tutor);

                Cachorro saved = cachorroService.save(cachorro);
                return new ResponseEntity(saved, HttpStatus.OK);
            }
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
