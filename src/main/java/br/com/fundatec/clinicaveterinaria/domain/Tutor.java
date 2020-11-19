package br.com.fundatec.clinicaveterinaria.domain;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "tutor")
public class Tutor {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "idade")
    private int idade;

    @OneToMany(mappedBy = "tutor", targetEntity = Cachorro.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cachorro> cachorros;

    public Tutor() {
    }

    public Tutor(Long id, String nome, int idade, List<Cachorro> cachorros) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cachorros = cachorros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<Cachorro> getCachorros() {
        return cachorros;
    }

    public void setCachorros(List<Cachorro> cachorros) {
        this.cachorros = cachorros;
    }
}
