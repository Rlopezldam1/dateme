package com.example.dateme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    ArrayList<Usuario> usuarios = GestorUsuarios.usuarios;

    private static int posUsuario = 0;

    private Usuario usuarioMostrado;

    @FXML
    private Button ajustes;

    @FXML
    private Button botonDislike;

    @FXML
    private Button botonLike;

    @FXML
    private Button chats;

    @FXML
    private Label campoNombre;

    @FXML
    private Label campoApellidos;

    @FXML
    private Label campoId;

    @FXML
    private Label campoLocalidad;

    @FXML
    private Label campoEdad;

    @FXML
    private Label campoDescripcion;

    @FXML
    private ImageView campoImagen;

    @FXML
    private Button matches;

    @FXML
    private Label mensajeMatch;

    @FXML
    private Button botonMatch;

    @FXML
    private Pane panelMensajeMatch;

    @FXML
    void ajustesClick(ActionEvent event) {

    }

    @FXML
    void chatsClick(ActionEvent event) {

    }

    @FXML
    void dislikeClick(ActionEvent event) {
        marcarPerfilVisitado();
        siguienteUsuario();
    }

    @FXML
    void likeClick(ActionEvent event) {
        marcarPerfilLikeado();
        marcarPerfilVisitado();
        if (comprobarMatch()) {
            botonLike.setVisible(false);
            botonDislike.setVisible(false);
            panelMensajeMatch.setVisible(true);
            mensajeMatch.setVisible(true);
            botonMatch.setVisible(true);
            String idUsuario = usuarioMostrado.getIdUsuario();
            mensajeMatch.setText("Has hecho match con " + idUsuario + ", se ha añadido al apartado de Matches.");
        } else {
            siguienteUsuario();
        }
    }

    @FXML
    void matchesClick(ActionEvent event) {

    }

    @FXML
    void continuarMatch(ActionEvent event) {
        mensajeMatch.setVisible(false);
        botonMatch.setVisible(false);
        panelMensajeMatch.setVisible(false);
        botonLike.setVisible(true);
        botonDislike.setVisible(true);
        siguienteUsuario();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        while (usuarios.get(posUsuario).equals(GestorUsuarios.usuarioActual)) {
            posUsuario++;
        }
        usuarioMostrado = usuarios.get(posUsuario);
        mostrarInformacionUsuario(usuarioMostrado);
        panelMensajeMatch.setVisible(false);
    }

    public void marcarPerfilVisitado() {
        GestorUsuarios.addUsuarioVisitado(usuarioMostrado);
    }

    public void marcarPerfilLikeado() {
        GestorUsuarios.addUsuarioLikeado(usuarioMostrado);
    }

    public boolean comprobarMatch() {
        if (GestorUsuarios.comprobarMatch(usuarioMostrado)) {
            GestorUsuarios.addMatch(usuarioMostrado);
            return true;
        }
        return false;
    }

    public void mostrarInformacionUsuario(Usuario usuario) {
        campoNombre.setText(usuario.getNombreUsuario());
        campoApellidos.setText(usuario.getApellidosUsuario());
        campoId.setText(usuario.getIdUsuario());
        campoLocalidad.setText(usuario.getLocalidadUsuario());
        campoEdad.setText(Integer.toString(usuario.getEdadUsuario()));
        campoDescripcion.setText(usuario.getDescripcionUsuario());
        campoImagen.setImage(usuario.getImagenUsuario());
    }

    public void siguienteUsuario() {
        if (posUsuario <= usuarios.size() - 2) {
            posUsuario++;
            //TODO Mensaje no se encuentran mas usuarios
        }
        while (usuarios.get(posUsuario).equals(GestorUsuarios.usuarioActual)) {
               // || GestorUsuarios.consultarUsuarioVisitado(usuarios.get(posUsuario))) {
            if (posUsuario <= usuarios.size() - 2) {
                posUsuario++;
                //TODO Mensaje no se encuentran mas usuarios
            }
        }
        usuarioMostrado = usuarios.get(posUsuario);
        mostrarInformacionUsuario(usuarioMostrado);
    }
}
