package com.LiterAlura.demo.Controller;

import Models.Libro;
import com.LiterAlura.demo.Repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/libros")
    public class LibroController {

        @Autowired
        private ILibroRepository libroRepository;

        // Obtener todos los libros
        @GetMapping
        public List<Libro> obtenerTodos() {
            return libroRepository.findAll();
        }

        // Obtener un libro por ID
        @GetMapping("/{id}")
        public Libro obtenerPorId(@PathVariable Long id) {
            return libroRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
        }

        // Crear un nuevo libro
        @PostMapping
        public Libro crearLibro(@RequestBody Libro libro) {
            return libroRepository.save(libro);
        }

        // Actualizar un libro
        @PutMapping("/{id}")
        public Libro actualizarLibro(@PathVariable Long id, @RequestBody Libro libroActualizado) {
            return libroRepository.findById(id).map(libro -> {
                libro.setTitulo(libroActualizado.getTitulo());
                libro.setAutores(libroActualizado.getAutores());
                // Actualiza más campos según corresponda
                return libroRepository.save(libro);
            }).orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
        }

        // Eliminar un libro
        @DeleteMapping("/{id}")
        public String eliminarLibro(@PathVariable Long id) {
            libroRepository.deleteById(id);
            return "Libro eliminado con éxito";
        }
    }


