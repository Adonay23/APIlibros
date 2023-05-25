package com.ues.bibliotecalibro.repository;

import com.ues.bibliotecalibro.entity.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<Biblioteca,Integer> {
}
