package com.ues.bibliotecalibro.controllers;

import javax.validation.Valid;

import com.ues.bibliotecalibro.responseDto.GenericResponse;
import com.ues.bibliotecalibro.entity.Libro;
import com.ues.bibliotecalibro.repository.BibliotecaRepository;
import com.ues.bibliotecalibro.repository.LibroRepository;

import com.ues.bibliotecalibro.responseDto.LibroRequestDto;
import com.ues.bibliotecalibro.responseDto.LibrosResponseDto;
import com.ues.bibliotecalibro.services.ILibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
public class LibroController {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ILibroService serviceLibro;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

 @Operation(summary = "Obtener todas los Libros", description = "Obtiene una lista de todos los libros",
            tags = {"Libros"})
    @ApiResponse(responseCode = "200", description = "ApiResponseMessages.ITEM_FETCHED",
            content ={@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Libro.class))})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad request o datos no enviados correctamente"),
            @ApiResponse(responseCode ="404", description = "Not found, no encontrado"),
            @ApiResponse(responseCode = "405", description = "No se encotraron libros en la base de datos"),
            @ApiResponse(responseCode = "200", description = "Peticion OK")})


    @GetMapping
    public ResponseEntity<Page<Libro>> listarLibros(Pageable pageable) {
      return serviceLibro.listar(pageable);
    }

    @GetMapping("/listaLibros")
    public ResponseEntity<Page<LibrosResponseDto>> listarLibrosByBiblioteca(Pageable pageable) {
        return serviceLibro.ListaLibros(pageable);
    }

    @PostMapping
    public ResponseEntity<GenericResponse> guardarLibro(@Valid @RequestBody LibroRequestDto libro) {
        return serviceLibro.registrar(libro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> actualizarLibro(@Valid @RequestBody LibroRequestDto libro, @PathVariable Integer id) {
        return serviceLibro.modificar(libro, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> eliminarLibro(@PathVariable Integer id) {
        return serviceLibro.eliminar(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Integer id) {
        return serviceLibro.buscarByAny(id);
    }
}
