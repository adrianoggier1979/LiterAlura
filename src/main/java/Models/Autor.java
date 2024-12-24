package Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String nombre;


    private Integer cumpleanios;


    private Integer fechaFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)



    private List<Libro> libros;

    public Autor(Long id, String nombre, Integer cumpleanios, Integer fechaFallecimiento) {
        this.id = id;
        this.nombre = nombre;
        this.cumpleanios = cumpleanios;
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public Autor(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
