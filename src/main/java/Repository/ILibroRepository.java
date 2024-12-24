package Repository;

import Models.Autor;
import Models.Idioma;
import Models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILibroRepository extends JpaRepository <Libro, Long>{

    boolean existsByTitulo(String titulo);

    Libro findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdioma(Idioma idioma);
    
    

    @Query("SELECT l FROM Libro l ORDER BY l.cantidadDescargas DESC LIMIT 10")
    List<Libro> findTop10ByTituloByCantidadDescargas();

    List<Libro> findByAutorNombre(String autorNombre);
}
