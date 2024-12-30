package com.LiterAlura.demo.Repository;

import Models.Idioma;
import Models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {

    boolean existsByTitulo(String titulo);

    Libro findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdioma(Idioma idioma);

    @Query("SELECT l FROM Libro l ORDER BY l.cantidadDescargas DESC")
    List<Libro> findTop10ByTituloByCantidadDescargas();

    @Query("SELECT l FROM Libro l JOIN l.autores a WHERE a.nombre = :autorNombre")
    List<Libro> findByAutorNombre(String autorNombre);
}