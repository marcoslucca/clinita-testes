package br.com.fundatec.clinicaveterinaria.controller;

import br.com.fundatec.clinicaveterinaria.controller.response.CollectionProjection;
import br.com.fundatec.clinicaveterinaria.controller.response.TutorProjection;
import br.com.fundatec.clinicaveterinaria.domain.Cachorro;
import br.com.fundatec.clinicaveterinaria.domain.Tutor;
import br.com.fundatec.clinicaveterinaria.service.TutorService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @GetMapping("{name}")
    public ResponseEntity<TutorProjection> findAllByNome(@PathVariable("name") String nome) {
        Tutor resultado = tutorService.findByNome(nome);

        return new ResponseEntity<>(TutorProjection.create(resultado), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionProjection> findAll() {
        List<TutorProjection> tutores = tutorService.findAll().stream().map(TutorProjection::create).collect(Collectors.toList());
        CollectionProjection resultado = new CollectionProjection(tutores);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/{nome}/cachorros")
    public ResponseEntity<List<Cachorro>> findAll(@PathVariable("nome") String nome) {
        List<Cachorro> resultado = tutorService.findAllCachorros(nome);

        if (resultado.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resultado);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tutor> salvaTutor(@RequestBody Tutor tutor) {
        tutorService.salvaTutor(tutor);

        return ResponseEntity.ok().build();
    }

}
