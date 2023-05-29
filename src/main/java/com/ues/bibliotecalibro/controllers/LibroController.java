package com.ues.bibliotecalibro.controllers;

import javax.validation.Valid;

import com.ues.bibliotecalibro.responseDto.GenericResponse;
import com.ues.bibliotecalibro.entity.Libro;
import com.ues.bibliotecalibro.repository.BibliotecaRepository;
import com.ues.bibliotecalibro.repository.LibroRepository;

import com.ues.bibliotecalibro.responseDto.LibrosResponseDto;
import com.ues.bibliotecalibro.services.ILibroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Obtener todas los Libros", notes = "No necesita parametros de entrada",
            responseContainer = "Libro")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response = Libro.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, no encontrado"),
            @ApiResponse(code = 405, message = "No se encotraron libros en la base de datos"),
            @ApiResponse(code = 200, message = "Peticion OK")})


    @GetMapping
    public ResponseEntity<Page<Libro>> listarLibros(Pageable pageable) {
      return serviceLibro.listar(pageable);
    }

    @GetMapping("/listaLibros")
    public ResponseEntity<Page<LibrosResponseDto>> listarLibrosByBiblioteca(Pageable pageable) {
        return serviceLibro.ListaLibros(pageable);
    }

    @PostMapping
    public ResponseEntity<GenericResponse> guardarLibro(@Valid @RequestBody Libro libro) {
        return serviceLibro.registrar(libro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> actualizarLibro(@Valid @RequestBody Libro libro, @PathVariable Integer id) {
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
