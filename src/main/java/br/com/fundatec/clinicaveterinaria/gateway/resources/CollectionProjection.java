package br.com.fundatec.clinicaveterinaria.gateway.resources;

import java.util.List;

public class CollectionProjection {
    private List<Consulta> items;

    public CollectionProjection(List<Consulta> items) {
        this.items = items;
    }

    public CollectionProjection() {
    }

    public List<Consulta> getItems() {
        return items;
    }

    public void setItems(List<Consulta> items) {
        this.items = items;
    }
}
