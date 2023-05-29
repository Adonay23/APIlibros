package com.ues.bibliotecalibro.repository;

import com.ues.bibliotecalibro.entity.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BibliotecaRepository extends JpaRepository<Biblioteca,Integer> {

    @Query(value = "SELECT id,nombre FROM biblioteca", nativeQuery = true)
    List<Biblioteca> mostrarAllBiblioteca();
}
