package Models.Record;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RAutor (
    @JsonAlias("name") String nombre,
    @JsonAlias("birth_year") Integer cumpleanios,
    @JsonAlias("death_year") Integer fechaFallecimiento
) {}


