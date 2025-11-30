package com.saborperu.backend.service;

import com.saborperu.backend.model.Producto;
import com.saborperu.backend.repository.ProductoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
    
    // Obtener producto por ID
    public Optional<Producto> obtenerPorId(@NonNull Long id) {
        return productoRepository.findById(id);
    }
    
    // Obtener productos por categoría
    public List<Producto> obtenerPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
    
    // Buscar productos por nombre
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    // Crear producto
    public Producto crear(@NonNull Producto producto) {
        return productoRepository.save(producto);
    }
    
    // Actualizar producto
    public Producto actualizar(@NonNull Long id, Producto productoActualizado) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setImagen(productoActualizado.getImagen());
                    producto.setStock(productoActualizado.getStock());
                    producto.setCategoria(productoActualizado.getCategoria());
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
    
    // Eliminar producto
    public void eliminar(@NonNull Long id) {
        productoRepository.deleteById(id);
    }
}
