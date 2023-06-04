package com.ues.bibliotecalibro.serviceImpl;

import com.ues.bibliotecalibro.entity.Biblioteca;
import com.ues.bibliotecalibro.responseDto.GenericResponse;
import com.ues.bibliotecalibro.entity.Libro;
import com.ues.bibliotecalibro.repository.BibliotecaRepository;
import com.ues.bibliotecalibro.repository.LibroRepository;
import com.ues.bibliotecalibro.responseDto.LibroRequestDto;
import com.ues.bibliotecalibro.responseDto.LibrosResponseDto;
import com.ues.bibliotecalibro.services.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibrosServiceImpl implements ILibroService {
    @Autowired
    LibroRepository libroRepository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Override
    public ResponseEntity<GenericResponse> registrar(LibroRequestDto request) {
        GenericResponse<Libro> rs = new GenericResponse<Libro>();

        try {
            Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(request.getIdbiblioteca());
            Libro librito=new Libro();

            librito.setId(request.getId());
            librito.setNombre(request.getNombre());
            librito.setBibliotequita(bibliotecaOptional.get());


            libroRepository.save(librito);
            rs.setCode(1);
            rs.setMessage("Exito -Libro Agregado");
            return new ResponseEntity<GenericResponse>(rs, HttpStatus.CREATED);
        } catch (Exception e) {
            rs.setCode(0);
            rs.setMessage("Error - No pudo agregarse el libro");
            return new ResponseEntity<GenericResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<GenericResponse> modificar(LibroRequestDto request, Integer id) {
        GenericResponse<Libro> rs = new GenericResponse<Libro>();
        try {
            Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(request.getIdbiblioteca());
            Optional<Libro> libroOptional = libroRepository.findById(id);
            Libro librito=new Libro();

            librito.setId(libroOptional.get().getId());
            librito.setNombre(request.getNombre());
            librito.setBibliotequita(bibliotecaOptional.get());

            libroRepository.save(librito);
            rs.setCode(1);
            rs.setMessage("Exito -Libro Actualizado");
            return new ResponseEntity<GenericResponse>(rs, HttpStatus.OK);
        } catch (Exception e) {
            rs.setCode(0);
            rs.setMessage("Error - No pudo modificarse el libro");
            return new ResponseEntity<GenericResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Page<Libro>> listar(Pageable pageable) {
        return ResponseEntity.ok(libroRepository.findAll(pageable));
    }

    @Override
    public ResponseEntity<Page<LibrosResponseDto>>  buscarByNombre(String name, Pageable pageable) {
        Page<LibrosResponseDto> librosMostrar = null;
        try {
            librosMostrar = libroRepository.findByName(name,pageable);

        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
        }
        return ResponseEntity.ok(librosMostrar);
    }


    @Override
    public ResponseEntity<GenericResponse> eliminar(Integer id) {
        GenericResponse<Libro> rs = new GenericResponse<Libro>();
        Optional<Libro> libroOptional = libroRepository.findById(id);
        if (libroOptional != null) {
            try {
                this.libroRepository.delete(libroOptional.get());
                rs.setCode(1);
                rs.setMessage("Exito -Libro eliminado");
                return new ResponseEntity<GenericResponse>(rs, HttpStatus.OK);
            } catch (Exception e) {
                rs.setCode(0);
                rs.setMessage("Error - Ocurrio un error inesperado");
                return new ResponseEntity<GenericResponse>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            rs.setCode(0);
            rs.setMessage("Error - No pudo eliminarse el libro");
            return new ResponseEntity<GenericResponse>(rs, HttpStatus.NOT_FOUND);
        }

    }


    @Override
    public ResponseEntity<Page<LibrosResponseDto>> ListaLibros(Pageable pageable) {

        Page<LibrosResponseDto> librosMostrar = null;
        try {
            librosMostrar = libroRepository.findAllBook(pageable);
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
        }
        return ResponseEntity.ok(librosMostrar);
    }

    @Override
    public ResponseEntity<Libro> buscarByAny(Integer id) {
        Optional<Libro> libroOptional = libroRepository.findById(id);
        if (!libroOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(libroOptional.get());
    }
}
