package br.com.fundatec.clinicaveterinaria.controller.response;

import br.com.fundatec.clinicaveterinaria.domain.Cachorro;

public class CachorroProjection {
    private Long id;
    private String name;
    private int age;

    public CachorroProjection(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public CachorroProjection() {
    }

    public static CachorroProjection create(Cachorro cachorro) {
        CachorroProjection projection = new CachorroProjection();

        projection.id = cachorro.getId();
        projection.name = cachorro.getNome();
        projection.age = cachorro.getIdade();

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
}
