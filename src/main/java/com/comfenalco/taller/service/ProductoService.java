package com.comfenalco.taller.service;

import com.comfenalco.taller.model.Producto;
import com.comfenalco.taller.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id: " + id));
    }

    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, Producto datosActualizados) {
        Producto existente = obtenerPorId(id);
        existente.setNombre(datosActualizados.getNombre());
        existente.setPrecio(datosActualizados.getPrecio());
        return productoRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new NoSuchElementException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }

    /**
     * Aplica un porcentaje de descuento a un precio base.
     *
     * @param precio               precio original
     * @param porcentajeDescuento  porcentaje a descontar (0-100)
     * @return precio final con el descuento aplicado
     */
    public double aplicarDescuento(double precio, double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100");
        }
        return precio - (precio * (porcentajeDescuento / 100.0));
    }
}
