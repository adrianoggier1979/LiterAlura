/*
package Models;

import Models.Record.DatosLibro;
import Models.Record.RAutor;
import Models.Idioma;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long libroId;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "libro_id") // Relaciona con el libro
    private List<Autor> autores;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Long cantidadDescargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.libroId = datosLibro.libroId();
        this.titulo = datosLibro.titulo();

        // Crear lista de autores a partir de RAutor
        if (datosLibro.autor() != null && !datosLibro.autor().isEmpty()) {
            this.autores = datosLibro.autor().stream()
                    .map(rAutor -> new Autor(null, rAutor.nombre(), rAutor.cumpleanios(), rAutor.fechaFallecimiento()))
                    .collect(Collectors.toList());
        } else {
            this.autores = null;
        }

        // Asignar idioma desde la lista
        if (datosLibro.idioma() != null && !datosLibro.idioma().isEmpty()) {
            this.idioma = Idioma.valueOf(datosLibro.idioma().get(0).toUpperCase());
        } else {
            this.idioma = null;
        }

        this.cantidadDescargas = datosLibro.cantidadDescargas();
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autores;
    }

    public void setAutor(List<Autor> autores) {
        this.autores = autores;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Long getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Long cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", libroId=" + libroId +
                ", titulo='" + titulo + '\'' +
                ", autores=" + (autores != null ? autores.toString() : "N/A") +
                ", idioma=" + idioma +
                ", cantidadDescargas=" + cantidadDescargas +
                '}';
    }
}
*/



package Models;


import Models.Record.DatosLibro;
import Models.Record.RAutor;
import Models.Idioma;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long libroId;

    @Column(unique = true)
    private String titulo;

    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "libro")
   // @JoinColumn(name = "autor_id")
    //@Transient
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private List<Autor> autores;
    

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Long cantidadDescargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.libroId = datosLibro.libroId();
        this.titulo = datosLibro.titulo();

        if (!datosLibro.autor().isEmpty()){
            this.autores = datosLibro.autor().stream()
                    .map(rAutor -> new Autor(null, rAutor.nombre(), rAutor.cumpleanios(), rAutor.fechaFallecimiento()))
                    .collect(Collectors.toList());

        }else {
            this.autores = null;
        }



        if (!datosLibro.idioma().isEmpty()){
            this.idioma = Idioma.valueOf(datosLibro.idioma().get(0).toUpperCase());

        }else {
            this.idioma = null;
        }




        this.cantidadDescargas = datosLibro.cantidadDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autores;
    }

    public void setAutor(List<Autor> autores) {
        this.autores = autores;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }


    public Long getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Long cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return
                "  \nid=" + id +
                        "  \nLibro id=" + libroId +
                        ", \ntitulo='" + titulo + '\'' +
                        ", \nauthors=" + (autores != null && !autores.isEmpty() ? autores.get(0).getNombre() : "N/A") +

                        ", \nidioma=" + idioma +

                        ", \ncantidadDescargas=" + cantidadDescargas;
    }
}

