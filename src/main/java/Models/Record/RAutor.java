package Models.Record;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class RAutor {

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("birth_year")
    private Integer cumpleanios;

    @JsonProperty("death_year")
    private Integer fechaFallecimiento;

    // Getters and setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCumpleanios() {
        return cumpleanios;
    }

    public void setCumpleanios(Integer cumpleanios) {
        this.cumpleanios = cumpleanios;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }
}