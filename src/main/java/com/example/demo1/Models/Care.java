package com.example.demo1.Models;

public class Care {
    String id, animal, nameCare;

    public Care(String id, String animal, String nameCare) {
        this.id = id;
        this.animal = animal;
        this.nameCare = nameCare;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getNameCare() {
        return nameCare;
    }

    public void setNameCare(String nameCare) {
        this.nameCare = nameCare;
    }
}
