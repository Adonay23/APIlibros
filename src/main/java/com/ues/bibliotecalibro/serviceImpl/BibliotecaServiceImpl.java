package com.ues.bibliotecalibro.serviceImpl;

import com.ues.bibliotecalibro.entity.Biblioteca;
import com.ues.bibliotecalibro.entity.Libro;
import com.ues.bibliotecalibro.repository.BibliotecaRepository;
import com.ues.bibliotecalibro.responseDto.BibliotecaResponseDto;
import com.ues.bibliotecalibro.responseDto.GenericResponse;
import com.ues.bibliotecalibro.services.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BibliotecaServiceImpl implements BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Override
    public ResponseEntity<List<BibliotecaResponseDto>> ListaBibliotecas() {
        List<BibliotecaResponseDto> listaB = new ArrayList<>();
        List<Biblioteca> lista = bibliotecaRepository.mostrarAllBiblioteca();

        for (Biblioteca bi : lista) {
            listaB.add(new BibliotecaResponseDto(bi.getId(), bi.getNombre()));
        }
        return ResponseEntity.ok(listaB);
    }

    @Override
    public ResponseEntity<GenericResponse> registrar(Biblioteca obj) {
        return null;
    }

    @Override
    public ResponseEntity<GenericResponse> modificar(Biblioteca obj, Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Page<Libro>> listar(Pageable pageable) {
        return null;
    }

    @Override
    public Biblioteca leerPorId(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<GenericResponse> eliminar(Integer id) {
        return null;
    }
}
