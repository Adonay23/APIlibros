package com.ues.bibliotecalibro.services;

import com.ues.bibliotecalibro.entity.Biblioteca;
import com.ues.bibliotecalibro.entity.Libro;
import com.ues.bibliotecalibro.responseDto.BibliotecaResponseDto;

import com.ues.bibliotecalibro.responseDto.GenericResponse;
import com.ues.bibliotecalibro.responseDto.LibroRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface BibliotecaService {

    ResponseEntity<List<BibliotecaResponseDto>> ListaBibliotecas();

    ResponseEntity<GenericResponse> registrar(LibroRequestDto obj);
    ResponseEntity<GenericResponse> modificar (LibroRequestDto obj,Integer id);
    ResponseEntity<Page<Libro>> listar(Pageable pageable);
    Biblioteca leerPorId(Integer id);
    ResponseEntity<GenericResponse>  eliminar(Integer id);

}
