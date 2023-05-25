package com.ues.bibliotecalibro.repository;

import com.ues.bibliotecalibro.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,Integer> {
}
