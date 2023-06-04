package com.ues.bibliotecalibro.services;

import com.ues.bibliotecalibro.entity.Libro;
import com.ues.bibliotecalibro.responseDto.GenericResponse;
import com.ues.bibliotecalibro.responseDto.LibroRequestDto;
import com.ues.bibliotecalibro.responseDto.LibrosResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface ILibroService {

    ResponseEntity<Page<LibrosResponseDto>> ListaLibros(Pageable pageable);

    ResponseEntity<Libro> buscarByAny(Integer id);

    ResponseEntity<GenericResponse> registrar(LibroRequestDto obj);
    ResponseEntity<GenericResponse> modificar (LibroRequestDto obj,Integer id);
    ResponseEntity<Page<Libro>> listar(Pageable pageable);
    Libro leerPorId(Integer id);
    ResponseEntity<GenericResponse>  eliminar(Integer id);

}
