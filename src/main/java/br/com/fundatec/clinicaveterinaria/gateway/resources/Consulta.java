package br.com.fundatec.clinicaveterinaria.gateway.resources;

public class Consulta {
    private Long id;
    private String vetName;
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", vetName='" + vetName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
