package com.example.dateme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
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
    private Label labelDescripcion;

    @FXML
    private ImageView imagenLocalidad;

    @FXML
    private Pane panelImagen;

    @FXML
    private Label mensajeUsuariosNoEncontrados;

    @FXML
    private Pane panelUsuariosNoEncontrados;

    @FXML
    void ajustesClick(ActionEvent event) {
        DateMeApp.cambiarPestana("settings/settings.fxml","Ajustes",900,600);
    }

    @FXML
    void chatsClick(ActionEvent event) {
        DateMeApp.cambiarPestana("chats/chats.fxml","Chats",900,600);
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
        DateMeApp.cambiarPestana("match/match.fxml","Matches",900,600);
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
        panelUsuariosNoEncontrados.setVisible(false);
        campoImagen.setPreserveRatio(false);
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

    public void usuariosNoEncontrados() {
        campoNombre.setVisible(false);
        campoApellidos.setVisible(false);
        campoId.setVisible(false);
        campoLocalidad.setVisible(false);
        campoEdad.setVisible(false);
        campoDescripcion.setVisible(false);
        labelDescripcion.setVisible(false);
        imagenLocalidad.setVisible(false);
        panelImagen.setVisible(false);
        campoImagen.setVisible(false);
        botonLike.setVisible(false);
        botonDislike.setVisible(false);
        panelUsuariosNoEncontrados.setVisible(true);

    }

    public void siguienteUsuario() {
        if (posUsuario <= usuarios.size() - 2) {
            posUsuario++;
        } else {
            usuariosNoEncontrados();
        }
        while (usuarios.get(posUsuario).equals(GestorUsuarios.usuarioActual)) { //TODO descomentar
               // || GestorUsuarios.consultarUsuarioVisitado(usuarios.get(posUsuario))) {
            if (posUsuario <= usuarios.size() - 2) {
                posUsuario++;
            }
            else {
                usuariosNoEncontrados();
                break;
            }
        }
        usuarioMostrado = usuarios.get(posUsuario);
        mostrarInformacionUsuario(usuarioMostrado);
    }

    public void inicioClick(ActionEvent actionEvent) {
        DateMeApp.cambiarPestana("mainpage/mainpage.fxml","DateMe",900,600);
    }

    public void clickId(MouseEvent mouseEvent) throws IOException {
        DateMeApp.cambiarChat(campoId.getText(),campoImagen.getImage());
    }
}
