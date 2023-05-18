package com.example.dateme;

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
}
