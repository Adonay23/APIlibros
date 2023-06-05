package com.ues.bibliotecalibro.responseDto;

public class LibrosResponseDto {
    private int id;
    private String nombre;

    private int idbiblioteca;
    private String biblioteca;

    public LibrosResponseDto(int id, String nombre, int idbiblioteca, String biblioteca) {
        this.id = id;
        this.nombre = nombre;
        this.idbiblioteca = idbiblioteca;
        this.biblioteca = biblioteca;
    }

    public int getIdbiblioteca() {
        return idbiblioteca;
    }

    public void setIdbiblioteca(int idbiblioteca) {
        this.idbiblioteca = idbiblioteca;
    }

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

    public String getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(String biblioteca) {
        this.biblioteca = biblioteca;
    }
}
