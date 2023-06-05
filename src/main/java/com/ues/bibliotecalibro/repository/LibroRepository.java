package com.ues.bibliotecalibro.repository;

import com.ues.bibliotecalibro.entity.Libro;

import com.ues.bibliotecalibro.responseDto.LibrosResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LibroRepository<E> extends JpaRepository<Libro, Integer> {

    @Query(value = "SELECT NEW com.ues.bibliotecalibro.responseDto.LibrosResponseDto ( b.id,b.nombre,a.id,a.nombre) FROM Biblioteca a JOIN a.libros b")
    Page<LibrosResponseDto> findAllBook(Pageable pageable);

    @Query(value="SELECT NEW com.ues.bibliotecalibro.responseDto.LibrosResponseDto ( b.id,b.nombre,a.id,a.nombre) FROM Biblioteca a JOIN a.libros b where b.nombre like %:nombre%")
    Page<LibrosResponseDto> findByName(String nombre, Pageable pageable);


}



