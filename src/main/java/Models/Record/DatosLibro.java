package Models.Record;

import Models.Autor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @Id
        @JsonAlias("id") Long libroId,
        @JsonAlias("title") String titulo,
        @JsonAlias("subjects") List<String> genero,
        @JsonAlias("authors") List<RAutor> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Long cantidadDescargas

) {
}
