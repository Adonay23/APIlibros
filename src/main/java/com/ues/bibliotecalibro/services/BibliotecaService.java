package com.ues.bibliotecalibro.services;

import com.ues.bibliotecalibro.entity.Biblioteca;
import com.ues.bibliotecalibro.responseDto.BibliotecaResponseDto;

import org.springframework.http.ResponseEntity;

import java.util.List;


public interface BibliotecaService extends ICRUD<Biblioteca>{

    ResponseEntity<List<BibliotecaResponseDto>> ListaBibliotecas();

}
