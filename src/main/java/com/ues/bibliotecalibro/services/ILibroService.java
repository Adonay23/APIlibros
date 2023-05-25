package com.ues.bibliotecalibro.services;

import com.ues.bibliotecalibro.entity.Libro;

import java.util.List;

public interface ILibroService extends ICRUD<Libro>{

    List<Libro> buscarLibro(String filtro);

    List<Libro> buscarByAny(String filtro);

}
