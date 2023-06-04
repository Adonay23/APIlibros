package com.ues.bibliotecalibro.responseDto;

public class LibroRequestDto {
    private int id;
    private String nombre;

    private int idbiblioteca;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdbiblioteca() {
        return idbiblioteca;
    }

    public void setIdbiblioteca(int idbiblioteca) {
        this.idbiblioteca = idbiblioteca;
    }
}
