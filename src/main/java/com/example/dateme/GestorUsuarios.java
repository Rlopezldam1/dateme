package com.example.dateme;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorUsuarios {
    public static ArrayList<Usuario> usuarios = GestionDatosBBDD.extraerUsuarios();
    public static Usuario usuarioActual;
    public static ArrayList<String> usuariosVisitados;
    public static ArrayList<String> usuariosLikeados;

    public static ArrayList<String> matches;

    public static void inicializarUsuarioActual(String id) {
        usuarioActual = GestorUsuarios.existeUsuario(id);
        usuariosVisitados = GestionDatosBBDD.extraerUsuariosVisitados(usuarioActual);
        usuariosLikeados = GestionDatosBBDD.extraerUsuariosLikeados(usuarioActual);
        matches = GestionDatosBBDD.extraerMatches(usuarioActual);
    }

    public static void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
        String[] infoUsuario = GestorUsuarios.parseInfoUsuario(usuario);
        GestionDatosBBDD.insertarUsuario(infoUsuario);
    }

    public static void addUsuarioVisitado(Usuario usuarioVisitado) {
        usuariosVisitados.add(usuarioVisitado.getIdUsuario());
        GestionDatosBBDD.insertarUsuarioVisitadoBBDD(usuarioActual, usuarioVisitado);
    }

    public static void addUsuarioLikeado(Usuario usuarioLikeado) {
        usuariosLikeados.add(usuarioLikeado.getIdUsuario());
        GestionDatosBBDD.insertarUsuarioLikeadoBBDD(usuarioActual, usuarioLikeado);
    }

    public static boolean comprobarMatch(Usuario usuarioLikeado) {
        return GestionDatosBBDD.comprobarMatch(usuarioActual, usuarioLikeado);
    }

    public static void addMatch(Usuario usuarioMatch) {
        matches.add(usuarioMatch.getIdUsuario());
        GestionDatosBBDD.insertarMatch(usuarioActual, usuarioMatch);
    }

    public static String[] parseInfoUsuario(Usuario usuario) {
        String idUsuario = usuario.getIdUsuario();
        String nombre = usuario.getNombreUsuario();
        String apellidos = usuario.getApellidosUsuario();
        String contraseña = usuario.getContraseñaUsuario();
        String localidad = usuario.getLocalidadUsuario();
        String correo = usuario.getCorreoUsuario();
        String genero = parseGenero(usuario.getGeneroUsuario());
        String preferenciaGenero = parsePreferenciaGenero(usuario.getPreferienciaGenero());
        String fechaNacimiento = parseFechaNacimiento(usuario.getFechaNacimientoUsuario());
        String preferenciaEdad = parsePreferenciaEdad(usuario.getPreferenciasEdad());
        String descripcion = usuario.getDescripcionUsuario();
        String foto = usuario.getIdUsuario() + ".jpg";
        String[] infoUsuario =
        {
            idUsuario, nombre, apellidos, contraseña, localidad, correo,
            genero, preferenciaGenero, fechaNacimiento, preferenciaEdad,
            descripcion, foto
        };
        return infoUsuario;
    }

    public static String parseGenero(Usuario.Genero genero) {
        if (genero.equals(Usuario.Genero.HOMBRE)) {
            return "Masculino";
        } else if (genero.equals(Usuario.Genero.MUJER)) {
            return "Femenino";
        }
        return "Otro";
    }

    public static String parsePreferenciaGenero(ArrayList<Usuario.Genero> generos) {
        if (generos.contains(Usuario.Genero.HOMBRE)) {
            if (generos.contains(Usuario.Genero.MUJER)) {
                return "Indiferente";
            }
            return "Masculino";
        }
        return "Femenino";
    }

    public static String parseFechaNacimiento(LocalDate fecha) {
        int año = fecha.getYear();
        int mes = fecha.getMonthValue();
        int dia = fecha.getDayOfMonth();
        return año + "-" + mes + "-" + dia;
    }

    public static String parsePreferenciaEdad(ArrayList<Integer> edades) {
        ArrayList<String> preferenciaEdadSeparado = new ArrayList<>();
        String preferenciaEdad = "";
        if (edades.contains(18)) {
            preferenciaEdadSeparado.add("18:25");
        }
        if (edades.contains(26)) {
            preferenciaEdadSeparado.add("26:30");
        }
        if (edades.contains(31)) {
            preferenciaEdadSeparado.add("31:40");
        }
        if (edades.contains(41)) {
            preferenciaEdadSeparado.add("41:50");
        }
        if (edades.contains(51)) {
            preferenciaEdadSeparado.add("51:60");
        }
        if (edades.contains(61)) {
            preferenciaEdadSeparado.add("61:70");
        }
        if (edades.contains(71)) {
            preferenciaEdadSeparado.add("71:80");
        }
        if (edades.contains(81)) {
            preferenciaEdadSeparado.add("81:90");
        }
        if (edades.contains(91)) {
            preferenciaEdadSeparado.add("91:100");
        }
        for (String s : preferenciaEdadSeparado) {
            preferenciaEdad += (s + "-");
        }
        preferenciaEdad = preferenciaEdad.substring(0, preferenciaEdad.length() - 1);
        return preferenciaEdad;
    }

    public static boolean consultarUsuarioVisitado(Usuario usuarioVisitado) {
        return GestionDatosBBDD.consultarUsuarioVisitadoBBDD(usuarioActual, usuarioVisitado);
    }

    public ArrayList<Usuario> generoCompatible(Usuario.Genero genero) {
        ArrayList<Usuario> usuariosCompatibles = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getGeneroUsuario().equals(genero)) {
                usuariosCompatibles.add(usuario);
            }
        }
        return usuariosCompatibles;
    }

    public static Usuario existeUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public ArrayList<Usuario> edadCompatible(ArrayList<ArrayList<Integer>> arraysEdades) {
        ArrayList<Integer> todasLasEdades = new ArrayList<>();
        for (ArrayList<Integer> arrayEdades : arraysEdades) {
            todasLasEdades.addAll(arrayEdades);
        }
        ArrayList<Usuario> usuariosCompatibles = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (todasLasEdades.contains(usuario.getEdadUsuario())) {
                usuariosCompatibles.add(usuario);
            }
        }
        return usuariosCompatibles;
    }

    public ArrayList<Usuario> localidadCompatible(String localidad) {
        ArrayList<Usuario> usuariosCompatibles = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getLocalidadUsuario().equals(localidad));
            usuariosCompatibles.add(usuario);
        }
        return usuariosCompatibles;
    }

    public static void main(String[] args) {
        String id = "josebatrigo";
        String nom = "Joseba";
        String ap = "Trigo";
        String co = "root";
        String loc = "Navarra";
        String corr = "jtrigosier1@educacion.navarra.es";
        LocalDate fechaNacimiento = LocalDate.of(2004, 1, 8);
        String des = "Me gustan las tetas grandes";
        Usuario.Genero gen = Usuario.Genero.HOMBRE;
        Image im = null;
        ArrayList<Usuario.Genero> preG = new ArrayList<>();
        preG.add(Usuario.Genero.MUJER);
        ArrayList<Integer> preE = new ArrayList<>();
        preE.add(18);
        Usuario usuario = new Usuario(id, nom, ap, co, loc, corr, fechaNacimiento, des, gen, im, preG, preE);
        GestorUsuarios.addUsuario(usuario);
    }
}
