package br.com.fundatec.clinicaveterinaria.controller.response;

import java.util.List;

public class CollectionProjection {
    private List<TutorProjection> items;

    public CollectionProjection(List<TutorProjection> items) {
        this.items = items;
    }

    public CollectionProjection() {
    }

    public List<TutorProjection> getItems() {
        return items;
    }

    public void setItems(List<TutorProjection> items) {
        this.items = items;
    }
}
