package com.ues.bibliotecalibro.responseDto;

public class LibrosResponseDto {
    private int id;
    private String nombre;
    private String biblioteca;

    public LibrosResponseDto(int id, String nombre, String biblioteca) {
        this.id = id;
        this.nombre = nombre;
        this.biblioteca = biblioteca;
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
