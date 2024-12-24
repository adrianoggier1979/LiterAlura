package Repository;

import Libreria.Libreria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"Models"})
@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

    @Autowired
    private ILibroRepository libroRepository;
    @Autowired
    private IAutorRepository autorRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }
        @Override
        public void run(String... args) throws Exception {

            Libreria libreria = new Libreria(libroRepository, autorRepository);
            libreria.consumo();

        }

}

