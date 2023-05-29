package com.ues.bibliotecalibro.services;

import com.ues.bibliotecalibro.entity.Libro;
import com.ues.bibliotecalibro.responseDto.LibrosResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface ILibroService extends ICRUD<Libro>{

    ResponseEntity<Page<LibrosResponseDto>> ListaLibros(Pageable pageable);

    ResponseEntity<Libro> buscarByAny(Integer id);



}
