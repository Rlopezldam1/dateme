package com.example.dateme;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;

/*  Clase para guardad
 *  y manipular la informacion
 *  de los perfiles de usuario
 */
public class Usuario {

    // Caracteristicas del pedfil del usuario
    private String idUsuario;
    private String nombreUsuario;
    private String apellidosUsuario;
    private String contraseña;
    private int edadUsuario;
    private String localidadUsuario;
    private String correoUsuario;
    private LocalDate fechaNacimientoUsuario;
    private String descripcionUsuario;
    private Image imagenUsuario;
    private Genero generoUsuario;
    private ArrayList<Genero> preferienciaGenero;
    private ArrayList<Integer> preferenciasEdad;

    public enum Genero {
        HOMBRE, MUJER, OTRO
    }

    /*
    * Constructor con parametros de todos los atributos
    */
    public Usuario(String idUsuario, String nombreUsuario, String apellidosUsuario, String contraseña,
                   String localidadUsuario, String correoUsuario, LocalDate fechaNacimientoUsuario,
                   String descripcionUsuario, Genero generoUsuario, Image imagenUsuario,
                   ArrayList<Genero> preferenciasGenero, ArrayList<Integer> preferenciasEdad) {

        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.contraseña = contraseña;
        this.localidadUsuario = localidadUsuario;
        this.correoUsuario = correoUsuario;
        this.fechaNacimientoUsuario = fechaNacimientoUsuario;
        this.descripcionUsuario = descripcionUsuario;
        this.generoUsuario = generoUsuario;
        this.imagenUsuario = imagenUsuario;
        this.preferienciaGenero = preferenciasGenero;
        this.preferenciasEdad = preferenciasEdad;
        this.edadUsuario = calcularEdad();
    }

    /*
     * Getters y setters de los atributos
     * del usuario
     */

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public int getEdadUsuario() {
        return edadUsuario;
    }

    public String getLocalidadUsuario() {
        return localidadUsuario;
    }

    public String getCorreoUsuario() {return correoUsuario;}

    public LocalDate getFechaNacimientoUsuario() {
        return fechaNacimientoUsuario;
    }

    public String getDescripcionUsuario() {
        return descripcionUsuario;
    }

    public Image getImagenUsuario() {
        return imagenUsuario;
    }

    public Genero getGeneroUsuario() {
        return generoUsuario;
    }

    public ArrayList<Genero> getPreferienciaGenero() {
        return preferienciaGenero;
    }

    public ArrayList<Integer> getPreferenciasEdad() {return preferenciasEdad;}

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public void setEdadUsuario(int edadUsuario) {
        this.edadUsuario = edadUsuario;
    }

    public void setLocalidadUsuario(String localidadUsuario) {
        this.localidadUsuario = localidadUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setFechaNacimientoUsuario(LocalDate fechaNacimientoUsuario) {
        this.fechaNacimientoUsuario = fechaNacimientoUsuario;
    }

    public void setDescripcionUsuario(String descripcionUsuario) {
        this.descripcionUsuario = descripcionUsuario;
    }

    public void setImagenUsuario(Image imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }

    public void setGeneroUsuario(Genero generoUsuario) {
        this.generoUsuario = generoUsuario;
    }

    public void setPreferienciaGenero(ArrayList<Genero> preferienciaGenero) {this.preferienciaGenero = preferienciaGenero;}

    public void setPreferenciasEdad(ArrayList<Integer> preferenciasEdad) {this.preferenciasEdad = preferenciasEdad;}

    /*
    * Calcula la edad del usuario usando su fecha de nacimiento
    */
    public int calcularEdad() {
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();
        int diaActual = fechaActual.getDayOfMonth();
        int mesUsuario = fechaNacimientoUsuario.getMonthValue();
        int diaUsuario = fechaNacimientoUsuario.getDayOfMonth();
        int edadAux = fechaActual.compareTo(fechaNacimientoUsuario);
        if ((mesActual > mesUsuario) || (mesActual == mesUsuario && diaActual > diaUsuario)) {
            edadAux--;
        }
        return edadAux;
    }
}
