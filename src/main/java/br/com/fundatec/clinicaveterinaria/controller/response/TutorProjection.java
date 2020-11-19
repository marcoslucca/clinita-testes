package br.com.fundatec.clinicaveterinaria.controller.response;

import br.com.fundatec.clinicaveterinaria.domain.Tutor;
import java.util.List;
import java.util.stream.Collectors;

public class TutorProjection {
    private Long id;
    private String name;
    private int age;
    private List<CachorroProjection> dogs;

    public TutorProjection(Long id, String name, int age, List<CachorroProjection> dogs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dogs = dogs;
    }

    public TutorProjection() {
    }

    public static TutorProjection create(Tutor tutor) {
        TutorProjection projection = new TutorProjection();

        projection.id = tutor.getId();
        projection.name = tutor.getNome();
        projection.age = tutor.getIdade();
        projection.dogs = tutor.getCachorros().stream().map(CachorroProjection::create).collect(Collectors.toList());

        return projection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<CachorroProjection> getDogs() {
        return dogs;
    }

    public void setDogs(List<CachorroProjection> dogs) {
        this.dogs = dogs;
    }
}
