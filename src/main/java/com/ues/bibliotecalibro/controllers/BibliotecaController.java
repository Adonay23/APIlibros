package com.ues.bibliotecalibro.controllers;

import com.ues.bibliotecalibro.entity.Biblioteca;
import com.ues.bibliotecalibro.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;



    @GetMapping
    public ResponseEntity<Page<Biblioteca>> listarBibliotecas(Pageable pageable) {
        return ResponseEntity.ok(bibliotecaRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Biblioteca> guardarBiblioteca(@RequestBody Biblioteca biblioteca) {
        Biblioteca bibliotecaguarda = bibliotecaRepository.save(biblioteca);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bibliotecaguarda.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(bibliotecaguarda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> actualizarBiblioteca
            (@PathVariable Integer id, @RequestBody Biblioteca biblioteca) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);
        if (!bibliotecaOptional.isPresent()) {

            return ResponseEntity.unprocessableEntity().build();
        } else {
            biblioteca.setId(bibliotecaOptional.get().getId());
            bibliotecaRepository.save(biblioteca);
            return ResponseEntity.noContent().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Biblioteca> eliminarBiblioteca(@PathVariable Integer id) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);
        if (!bibliotecaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        bibliotecaRepository.delete(bibliotecaOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca>obtenerBibliotecsPorId(@PathVariable Integer id){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);
        if (!bibliotecaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(bibliotecaOptional.get());
    }
}