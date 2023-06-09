package com.example.dateme;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
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
                String fechaNacimientoStr = resultados.getString("fecha_nacimiento");
                LocalDate fechaNacimiento = parseFecha(fechaNacimientoStr);
                String preferenciaEdadStr = resultados.getString("preferencia_edad");
                String descripción = resultados.getString("descripcion");
                String rutaImagen = resultados.getString("foto");
                Image imagen = cargarImagen(rutaImagen);
                Usuario.Genero genero = parseGenero(generoStr);
                ArrayList<Usuario.Genero> preferenciaGenero = parsePreferenciaGenero(preferenciaGeneroStr);
                ArrayList<Integer> preferenciaEdad = parsePreferenciaEdad(preferenciaEdadStr);

                Usuario usuario = new Usuario(id_usuario, nombre, apellidos, contraseña, localidad, correo, fechaNacimiento, descripción, genero, imagen, preferenciaGenero, preferenciaEdad);
                listaUsuarios.add(usuario);
            }
            resultados.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public static ArrayList<String> extraerUsuariosVisitados(Usuario usuario) {
        ArrayList<String> usuariosVisitados = new ArrayList<>();
        Connection connection = SQLiteConnection.conectar();
        Statement statement = null;
        String idUsuario = usuario.getIdUsuario();
        try {
            String sql = "SELECT * FROM visitados WHERE user_id_1 = '" + idUsuario + "'";
            statement = connection.createStatement();
            ResultSet resultados = statement.executeQuery(sql);

            while (resultados.next()) {
                String idUsuarioVisitado = resultados.getString("user_id_2");
                usuariosVisitados.add(idUsuarioVisitado);
            }
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return usuariosVisitados;
    }

    public static ArrayList<String> extraerUsuariosLikeados(Usuario usuario) {
        ArrayList<String> usuariosVisitados = new ArrayList<>();
        Connection connection = SQLiteConnection.conectar();
        Statement statement = null;
        String idUsuario = usuario.getIdUsuario();
        try {
            String sql = "SELECT * FROM visitados WHERE user_id_1 = '" + idUsuario + "'";
            statement = connection.createStatement();
            ResultSet resultados = statement.executeQuery(sql);

            while (resultados.next()) {
                String idUsuarioVisitado = resultados.getString("user_id_2");
                usuariosVisitados.add(idUsuarioVisitado);
            }
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return usuariosVisitados;
    }

    public static ArrayList<String> extraerMatches(Usuario usuario) {
        ArrayList<String> usuariosVisitados = new ArrayList<>();
        Connection connection = SQLiteConnection.conectar();
        Statement statement = null;
        String idUsuario = usuario.getIdUsuario();
        try {
            String sql = "SELECT * FROM match WHERE user_id_1 = '" + idUsuario + "'";
            statement = connection.createStatement();
            ResultSet resultados = statement.executeQuery(sql);

            while (resultados.next()) {
                String idUsuarioVisitado = resultados.getString("user_id_2");
                usuariosVisitados.add(idUsuarioVisitado);
            }
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return usuariosVisitados;
    }

    public static LocalDate parseFecha(String fechaStr) {
        String[] splitFecha = fechaStr.split("-");
        int año = Integer.parseInt(splitFecha[0]);
        int mes = Integer.parseInt(splitFecha[1]);
        int dia = Integer.parseInt(splitFecha[2]);
        LocalDate fecha = LocalDate.of(año, mes, dia);
        return fecha;
    }

    public static Image cargarImagen(String archivo) {
        Image imagen = null;
        String ruta = "fotosperfil/" + archivo;
        URL url = GestionDatosBBDD.class.getResource(ruta);
        try {
            if (url != null) {
                imagen = new Image(url.openStream());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return imagen;
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

    public static void insertarUsuario(String[] infoUsuario) {
        Connection connection = SQLiteConnection.conectar();
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO usuarios VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            for (int i = 1; i <= 12; i++) {
                statement.setString(i, infoUsuario[i - 1]);
            }
            statement.execute();
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void insertarUsuarioVisitadoBBDD(Usuario usuario, Usuario usuarioVisitado) {
        String idUsuario = usuario.getIdUsuario();
        String idUsuarioVisitado = usuarioVisitado.getIdUsuario();
        Connection connection = SQLiteConnection.conectar();
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO visitados (user_id_1, user_id_2) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, idUsuario);
            statement.setString(2, idUsuarioVisitado);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static boolean consultarUsuarioVisitadoBBDD(Usuario usuario, Usuario usuarioVisitado) {
        String idUsuario = usuario.getIdUsuario();
        String idUsuarioVisitado = usuarioVisitado.getIdUsuario();
        Connection connection = SQLiteConnection.conectar();
        Statement statement = null;

        try {
            String sql = "SELECT * FROM visitados WHERE user_id_1 = '" + idUsuario + "' and user_id_2 = '" + idUsuarioVisitado + "'";
            statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(sql);
            if (resultado.next()) {
                resultado.close();
                statement.close();
                connection.close();
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static boolean comprobarMatch(Usuario usuario, Usuario usuarioLikeado) {
        String idUsuario = usuario.getIdUsuario();
        String idUsuarioLikeado = usuarioLikeado.getIdUsuario();
        Connection connection = SQLiteConnection.conectar();
        Statement statement = null;

        try {
            String sql = "SELECT * FROM likes WHERE user_id_1 = '" + idUsuarioLikeado + "' and user_id_2 = '" + idUsuario + "'";
            statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(sql);
            if (resultado.next()) {
                resultado.close();
                statement.close();
                connection.close();
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static void insertarUsuarioLikeadoBBDD(Usuario usuario, Usuario usuarioLikeado) {
        String idUsuario = usuario.getIdUsuario();
        String idUsuarioVisitado = usuarioLikeado.getIdUsuario();
        Connection connection = SQLiteConnection.conectar();
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO likes (user_id_1, user_id_2) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, idUsuario);
            statement.setString(2, idUsuarioVisitado);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public static void insertarMatch(Usuario usuario, Usuario otroUsuario) {
        String idUsuario = usuario.getIdUsuario();
        String idOtroUsuario = otroUsuario.getIdUsuario();
        Connection connection = SQLiteConnection.conectar();
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        try {
            String sql = "INSERT INTO match (user_id_1, user_id_2) VALUES (?, ?)";

            statement1 = connection.prepareStatement(sql);
            statement1.setString(1, idUsuario);
            statement1.setString(2, idOtroUsuario);
            statement1.execute();
            statement1.close();

            statement2 = connection.prepareStatement(sql);
            statement2.setString(1, idOtroUsuario);
            statement2.setString(2, idUsuario);
            statement2.execute();
            statement2.close();

            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
