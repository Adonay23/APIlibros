package com.ues.bibliotecalibro.services;

import com.ues.bibliotecalibro.responseDto.GenericResponse;
import com.ues.bibliotecalibro.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICRUD <T>{
    ResponseEntity<GenericResponse> registrar(T obj);
    ResponseEntity<GenericResponse> modificar (T obj,Integer id);
    ResponseEntity<Page<Libro>> listar(Pageable pageable);
    T leerPorId(Integer id);
    ResponseEntity<GenericResponse>  eliminar(Integer id);
}
