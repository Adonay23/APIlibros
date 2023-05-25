package com.ues.bibliotecalibro.serviceImpl;

import com.ues.bibliotecalibro.entity.Libro;
import com.ues.bibliotecalibro.repository.LibroRepository;
import com.ues.bibliotecalibro.services.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrosServiceImpl implements ILibroService {
    @Autowired
    LibroRepository libroRepository;

    @Override
    public Libro registrar(Libro obj) {
        return null;
    }

    @Override
    public Libro modificar(Libro obj) {
        return null;
    }

    @Override
    public List<Libro> listar() {
        return null;
    }

    @Override
    public Libro leerPorId(Integer id) {
        return null;
    }

    @Override
    public boolean eliminar(Libro obj) {
        try {
            this.libroRepository.delete(obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Libro> buscarLibro(String filtro) {
        return null;
    }

    @Override
    public List<Libro> buscarByAny(String filtro) {
        return null;
    }
}
