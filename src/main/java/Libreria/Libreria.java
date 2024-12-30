package Libreria;

import Configuracion.ConsumoApi;
import Configuracion.ConvierteDatos;
import Models.Autor;
import Models.Idioma;
import Models.Libro;
import Models.LibrosRespuestaApi;
import Models.Record.DatosLibro;
import com.LiterAlura.demo.Repository.IAutorRepository;
import com.LiterAlura.demo.Repository.ILibroRepository;
import com.LiterAlura.demo.Exceptions.LibroNoEncontradoException;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Libreria {
    private static String API_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos convertir = new ConvierteDatos();

    private ILibroRepository libroRepository;
    private IAutorRepository autorRepositoryRepository;

    public Libreria(ILibroRepository libroRepository, IAutorRepository autorRepositoryRepository) {
        this.libroRepository = libroRepository;
        this.autorRepositoryRepository = autorRepositoryRepository;
    }

    public void consumo() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    |*****       ELIJA LA OPCION DESEADA       ******|
                    
                    
                    1 - Agregar Libro por Nombre
                    2 - Libros buscados
                    3 - Buscar libro por Nombre
                    4 - Buscar libro por idioma
                    5 - Buscar libro por autor
                    
                    
                     0 - Salir
                    
                    |*****            INGRESE UNA OPCIÓN          ******|
                    """;

            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {


                System.out.println("|*****  Por favor, ingrese un número válido.  ******|");

                teclado.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarLibroEnLaWeb();
                    break;
                case 2:
                    librosBuscados();
                    break;
                case 3:
                    buscarLibroPorNombre();
                    break;
                case 4:
                    buscarLibroPorIdioma();
                    break;
                case 5:
                    buscarLibroPorAutor();
                    break;

                case 0:
                    opcion = 0;
                    System.out.println("|*****     CERRANDO APLICACION   *****|");
                    break;


                default:
                    System.out.println("|*****  ELIJA UNA OPCION CORRECTA *****|");

                    consumo();
                    break;


            }
        }

    }

    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese el nombre del libro: ");

        var nombreLibro = teclado.nextLine().toLowerCase();

        ConsumoApi consumoApi = new ConsumoApi();
        var json = consumoApi.obtenerDatos(API_BASE + nombreLibro.replace(" ", "%20"));
        System.out.println(json);
        LibrosRespuestaApi datos = convertir.convertirDatosJsonAJava(json, LibrosRespuestaApi.class);

        if (datos != null && datos.getResultadoLibros() != null && !datos.getResultadoLibros().isEmpty()) {
            return datos.getResultadoLibros().get(0);
            // DatosLibro primerLibro = datos.getResultadoLibros().get(0);
           // return new Libro(primerLibro);
        } else {
            throw new LibroNoEncontradoException("El libro no está disponible");
            //System.out.println("EL LIBRO NO ESTA DISPONIBLE");
          //  return null;
        }
    }



private void buscarLibroEnLaWeb() {
    try {
        DatosLibro datosLibro = getDatosLibro();
        Libro libro = new Libro(datosLibro);

        boolean libroExists = libroRepository.existsByTitulo(libro.getTitulo());
        if (libroExists) {
            System.out.println("El libro ya existe en la base de datos!");
        } else {
            libroRepository.save(libro);
            System.out.println(libro.toString());
        }
    } catch (LibroNoEncontradoException e) {
        System.out.println(e.getMessage());
    } catch (InvalidDataAccessApiUsageException e) {
        System.out.println("No se puede persisitir el libro buscado!");
    }
}

    @Transactional(readOnly = true)
    protected void librosBuscados(){

        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos.");
        } else {
            System.out.println("Libros encontrados en la base de datos:");
            for (Libro libro : libros) {
                System.out.println(libro.toString());
            }
        }
    }

    private void buscarLibroPorNombre() {
        System.out.println("Ingrese Nombre libro que quiere buscar: ");
        var titulo = teclado.nextLine();
        Libro libroBuscado = libroRepository.findByTituloContainsIgnoreCase(titulo);
        if (libroBuscado != null) {
            System.out.println("El libro buscado fue: " + libroBuscado);
        } else {
            System.out.println("El libro con el Nombre '" + titulo + "' no se encontró.");
        }
    }

    private void buscarLibroPorIdioma(){
        System.out.println("Ingrese el idioma quen desea buscar: (ES, EN, FR, PT)");
        String idiomaStr = teclado.nextLine();

        try {
            Idioma idioma = Idioma.valueOf(idiomaStr.toUpperCase());
            List<Libro> libros = libroRepository.findByIdioma(idioma);

            if(libros.isEmpty()){
                System.out.println("No se encontraron libros en el idioma " + idiomaStr);
            }else {
                System.out.println("Libros encontrados en el idioma " + idiomaStr + ":");
                for (Libro libro : libros){
                    System.out.println(libro.toString());
                }

            }

        }catch (IllegalArgumentException e){
            System.out.println("El idioma ingresado no es válido por favor ingrese ES, EN, FR o PT.");
        }

    }

    private void buscarLibroPorAutor(){
        System.out.println("Ingrese el nombre del autor que desea buscar: ");
        String autorNombre = teclado.nextLine();

        try {
            List<Libro> libros = libroRepository.findByAutorNombre(autorNombre);

            if(libros.isEmpty()){
                System.out.println("No se encontraron libros del autor: " + autorNombre);
            } else {
                System.out.println("Libros encontrados con el autor " + autorNombre + ":");
                for (Libro libro : libros) {
                    System.out.println(libro);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("El autor ingresado no está registrado");
        }
    }
}

