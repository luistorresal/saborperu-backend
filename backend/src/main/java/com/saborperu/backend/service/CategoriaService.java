package com.saborperu.backend.service;

import com.saborperu.backend.model.Categoria;
import com.saborperu.backend.repository.CategoriaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    
    // Obtener todas las categorías
    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }
    
    // Obtener categoría por ID
    public Optional<Categoria> obtenerPorId(@NonNull Long id) {
        return categoriaRepository.findById(id);
    }
    
    // Crear categoría
    public Categoria crear(@NonNull Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    // Actualizar categoría
    public Categoria actualizar(@NonNull Long id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(categoriaActualizada.getNombre());
                    categoria.setDescripcion(categoriaActualizada.getDescripcion());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
    }
    
    // Eliminar categoría
    public void eliminar(@NonNull Long id) {
        categoriaRepository.deleteById(id);
    }
}
