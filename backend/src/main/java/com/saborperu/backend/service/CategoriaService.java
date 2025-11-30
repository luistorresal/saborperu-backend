package com.saborperu.backend.service;

import com.saborperu.backend.model.Categoria;
import com.saborperu.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    // Obtener todas las categorías
    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }
    
    // Obtener categoría por ID
    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }
    
    // Crear categoría
    public Categoria crear(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    // Actualizar categoría
    public Categoria actualizar(Long id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(categoriaActualizada.getNombre());
                    categoria.setDescripcion(categoriaActualizada.getDescripcion());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
    }
    
    // Eliminar categoría
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}

