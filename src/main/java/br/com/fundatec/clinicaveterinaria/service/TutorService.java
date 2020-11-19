package br.com.fundatec.clinicaveterinaria.service;

import br.com.fundatec.clinicaveterinaria.domain.Cachorro;
import br.com.fundatec.clinicaveterinaria.domain.Tutor;
import br.com.fundatec.clinicaveterinaria.exception.NotFoundException;
import br.com.fundatec.clinicaveterinaria.repository.TutorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    public Tutor findByNome(String nome) {
        Optional<Tutor> resultado = tutorRepository.findByNome(nome);

        if (!resultado.isPresent()) {
            throw new NotFoundException("Tutor was not found");
        }

        return resultado.get();
    }

    public List<Cachorro> findAllCachorros(String nome) {
        return tutorRepository.findAllCachorros(nome);
    }

    public Tutor salvaTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }
}
