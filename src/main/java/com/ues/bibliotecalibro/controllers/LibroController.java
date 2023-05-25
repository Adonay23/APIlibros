package com.ues.bibliotecalibro.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.ues.bibliotecalibro.entity.Biblioteca;
import com.ues.bibliotecalibro.entity.GenericResponse;
import com.ues.bibliotecalibro.entity.Libro;
import com.ues.bibliotecalibro.repository.BibliotecaRepository;
import com.ues.bibliotecalibro.repository.LibroRepository;

import com.ues.bibliotecalibro.services.ILibroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/libros")


public class LibroController {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ILibroService serviceLibro;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;
    @ApiOperation(value = "Obtener todas los Libros", notes = "No necesita parametros de entrada",
            responseContainer = "Libro")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response=Libro.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, no encontrado"),
            @ApiResponse(code = 405, message = "No se encotraron libros en la base de datos"),
            @ApiResponse(code = 200, message = "Peticion OK")})



    @GetMapping
    public ResponseEntity<Page<Libro>> listarLibros(Pageable pageable){
        return ResponseEntity.ok(libroRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Libro> guardarLibro(@Valid @RequestBody Libro libro){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBibliotequita().getId());
        System.out.println("bibliotecaOptional: "+bibliotecaOptional);
        if(!bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        libro.setBibliotequita(bibliotecaOptional.get());
        Libro libroGuardado = libroRepository.save(libro);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(libroGuardado.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(libroGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@Valid @RequestBody Libro libro,@PathVariable Integer id){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBibliotequita().getId());
        if(!bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Libro> libroOptional = libroRepository.findById(id);
        if(!libroOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        libro.setBibliotequita(bibliotecaOptional.get());
        libro.setId(libroOptional.get().getId());
        libroRepository.save(libro);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Libro>> eliminarLibro(@PathVariable Integer id){
        Optional<Libro> libroOptional = libroRepository.findById(id);
        GenericResponse<Libro>rs=new GenericResponse<Libro>();
        rs.setResponse(libroOptional.get());

        if(libroOptional!=null){

            if(this.serviceLibro.eliminar(libroOptional.get())){
                rs.setCode(1);
                rs.setMessage("Exito -Libro eliminado");
                return new ResponseEntity<GenericResponse<Libro>>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                rs.setCode(0);
                rs.setMessage("Error - No pudo eliminarse el libro");
                return new ResponseEntity<GenericResponse<Libro>>(rs, HttpStatus.NOT_FOUND);
            }

        }else{
            rs.setCode(0);
            rs.setMessage("Error- No pudo encontrarse el libro");
            return new ResponseEntity<GenericResponse<Libro>>(rs, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Integer id){
        Optional<Libro> libroOptional = libroRepository.findById(id);
        if(!libroOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(libroOptional.get());
    }
}
