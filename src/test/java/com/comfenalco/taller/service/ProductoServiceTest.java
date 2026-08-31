package com.comfenalco.taller.service;

import com.comfenalco.taller.model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Test
    void debeCalcularPrecioConDescuento() {
        double resultado = productoService.aplicarDescuento(100.0, 10);
        assertEquals(90.0, resultado);
    }

    @Test
    void debeLanzarExcepcionSiDescuentoEsInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> productoService.aplicarDescuento(100.0, 150));
    }

    @Test
    void debeCrearYObtenerProducto() {
        Producto creado = productoService.crear(new Producto("Teclado", 50000));
        Producto obtenido = productoService.obtenerPorId(creado.getId());

        assertEquals("Teclado", obtenido.getNombre());
        assertEquals(50000, obtenido.getPrecio());
    }

    @Test
    void debeActualizarProducto() {
        Producto creado = productoService.crear(new Producto("Mouse", 30000));
        Producto actualizado = productoService.actualizar(creado.getId(), new Producto("Mouse Gamer", 45000));

        assertEquals("Mouse Gamer", actualizado.getNombre());
        assertEquals(45000, actualizado.getPrecio());
    }

    @Test
    void debeEliminarProducto() {
        Producto creado = productoService.crear(new Producto("Monitor", 700000));
        productoService.eliminar(creado.getId());

        assertThrows(java.util.NoSuchElementException.class,
                () -> productoService.obtenerPorId(creado.getId()));
    }
}
