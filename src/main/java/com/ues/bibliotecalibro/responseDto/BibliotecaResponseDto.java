package com.ues.bibliotecalibro.responseDto;

public class BibliotecaResponseDto {
    private int id;
    private String nombre;

    public BibliotecaResponseDto(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
