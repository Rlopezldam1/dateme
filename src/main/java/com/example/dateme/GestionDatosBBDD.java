package com.example.dateme;
import javafx.scene.image.Image;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionDatosBBDD {

    private static final Integer[] EDADES1 = {18,19,20,21,22,23,24,25};
    private static final Integer[] EDADES2 = {26,27,28,29,30};
    private static final Integer[] EDADES3 = {31,32,33,34,35,36,37,38,39,40};
    private static final Integer[] EDADES4 = {41,42,43,44,45,46,47,48,49,50};
    private static final Integer[] EDADES5 = {51,52,53,54,55,56,57,58,59,60};
    private static final Integer[] EDADES6 = {61,62,63,64,65,66,67,68,69,70};
    private static final Integer[] EDADES7 = {71,72,73,74,75,76,77,78,79,80};
    private static final Integer[] EDADES8 = {81,82,83,84,85,86,87,88,89,90};
    private static final Integer[] EDADES9 = {91,92,93,94,95,96,97,98,99,100};

    public static ArrayList<Usuario> extraerUsuarios() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Connection connection = SQLiteConnection.conectar();
        Statement statement = null;
        try {
            String sql = "SELECT * FROM usuarios";
            statement = connection.createStatement();
            ResultSet resultados = statement.executeQuery(sql);

            while (resultados.next()) {
                String id_usuario = resultados.getString("id_usuario");
                String nombre = resultados.getString("nombre");
                String apellidos = resultados.getString("apellidos");
                String contraseña = resultados.getString("contraseña");
                String localidad = resultados.getString("localidad");
                String correo = resultados.getString("correo_electronico");
                String generoStr = resultados.getString("genero");
                String preferenciaGeneroStr = resultados.getString("preferencia_genero");
                //TODO solucionar error fechas
                //Date fechaNacimientoAux = resultados.getDate("fecha_nacimiento");
                //LocalDate fechaNacimiento = LocalDate.of(fechaNacimientoAux.getYear(), fechaNacimientoAux.getMonth(), fechaNacimientoAux.getDay());
                LocalDate fechaNacimiento = LocalDate.now();
                String preferenciaEdadStr = resultados.getString("preferencia_edad");
                String descripción = resultados.getString("descripcion");
                String rutaImagen = resultados.getString("foto");

                //TODO gestionar imagenes
                Image imagen = null;
                Usuario.Genero genero = parseGenero(generoStr);
                ArrayList<Usuario.Genero> preferenciaGenero = parsePreferenciaGenero(preferenciaGeneroStr);
                ArrayList<Integer> preferenciaEdad = parsePreferenciaEdad(preferenciaEdadStr);

                Usuario usuario = new Usuario(id_usuario, nombre, apellidos, contraseña, localidad, correo, fechaNacimiento, descripción, genero, imagen, preferenciaGenero, preferenciaEdad);
                listaUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public static ArrayList<Usuario.Genero> parsePreferenciaGenero(String preferencia) {
        ArrayList<Usuario.Genero> preferenciaGenero = new ArrayList<>();
        if (preferenciaGenero.equals("Masculino")) {
            preferenciaGenero.add(Usuario.Genero.HOMBRE);
        } else if (preferencia.equals("Femenino")) {
            preferenciaGenero.add(Usuario.Genero.MUJER);
        } else if (preferencia.equals("Indiferente")) {
            preferenciaGenero.add(Usuario.Genero.HOMBRE);
            preferenciaGenero.add(Usuario.Genero.MUJER);
        }
        return preferenciaGenero;
    }

    public static Usuario.Genero parseGenero(String generoStr) {
        Usuario.Genero genero = Usuario.Genero.OTRO;
        if (generoStr.equals("Masculino")) {
            genero = Usuario.Genero.HOMBRE;
        } else if (generoStr.equals("Femenino")) {
            genero = Usuario.Genero.MUJER;
        }
        return genero;
    }

    public static ArrayList<Integer> parsePreferenciaEdad(String preferencia) {
        ArrayList<Integer> preferenciaEdad = new ArrayList<>();
        if (preferencia.contains("18:25")) {
            preferenciaEdad.addAll(List.of(EDADES1));
        }
        if (preferencia.contains("26:30")) {
            preferenciaEdad.addAll(List.of(EDADES2));
        }
        if (preferencia.contains("31:40")) {
            preferenciaEdad.addAll(List.of(EDADES3));
        }
        if (preferencia.contains("41:50")) {
            preferenciaEdad.addAll(List.of(EDADES4));
        }
        if (preferencia.contains("51:60")) {
            preferenciaEdad.addAll(List.of(EDADES5));
        }
        if (preferencia.contains("61:70")) {
            preferenciaEdad.addAll(List.of(EDADES6));
        }
        if (preferencia.contains("71:80")) {
            preferenciaEdad.addAll(List.of(EDADES7));
        }
        if (preferencia.contains("81:90")) {
            preferenciaEdad.addAll(List.of(EDADES8));
        }
        if (preferencia.contains("91:100")) {
            preferenciaEdad.addAll(List.of(EDADES9));
        }
        return preferenciaEdad;
    }

    public void pintar(ArrayList<Usuario> lista) {
        StringBuilder sb = new StringBuilder();
        for (Usuario usuario : lista) {
            sb.append("\t").append("-".repeat(20)).append("\n");
            sb.append("\tID: ").append(usuario.getIdUsuario()).append("\n");
            sb.append("\tNombre: ").append(usuario.getNombreUsuario());
            sb.append("\tApellidos: ").append(usuario.getApellidosUsuario()).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        SQLiteConnection.ejecutarScriptSQL("dateme.sql");

        GestionDatosBBDD g = new GestionDatosBBDD();
        g.pintar(GestionDatosBBDD.extraerUsuarios());
    }
}
