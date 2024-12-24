package Configuracion;

public interface IConvierteDatos {

    <T> T convertirDatosJsonAJava(String json, Class<T> clase);
}